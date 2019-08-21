/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.controller;

import com.bsp.pos.model.Opname;
import com.bsp.pos.model.OpnameProduk;
import com.bsp.pos.model.Produk;
import com.bsp.pos.proxy.DetailOpnameProxy;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.OpnameProdukService;
import com.bsp.pos.service.OpnameService;
import com.bsp.pos.service.ProdukService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/opname")
@Api(value = "OpnameController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Opname.class)})
public class OpnameController {
    OpnameService os = new OpnameService();
    OpnameProdukService ops = new OpnameProdukService();
    ProdukService ps = new ProdukService();
    
    @RequestMapping(method = RequestMethod.GET)
    public Response getAll(){
        try {
            List<Opname> opnames = os.getAll();
            if(opnames != null){
                return new Response(true, null, "data berhasil ditampilkan", opnames);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", opnames);
            }
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Response getById(@PathVariable("id") Integer id){
        try {
            List<Opname> opnames = new ArrayList<Opname>();
            opnames.add(os.getById(id));
            if(opnames != null){
                return new Response(true, null, "data berhasil ditampilkan", opnames);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", opnames);
            }
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
    @RequestMapping(path = "/detail/{id}",method = RequestMethod.GET)
    public Response detail(@PathVariable("id") Integer id){
        try {
            List<DetailOpnameProxy> detailOpnames = ops.detail(id);
            if(detailOpnames != null){
                return new Response(true, null, "data berhasil ditampilkan", detailOpnames);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", detailOpnames);
            }
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Response add(@RequestBody Opname opname){
        
        try {
            OpnameProduk opnameproduk = new OpnameProduk();
   
            opname = os.save(opname);
            List<Produk> listProduk = ps.getAll();
            opnameproduk.setOpname(opname);
            for(Produk produk : listProduk){
                opnameproduk.setProduk(produk);
                ops.save(opnameproduk);
            }
            
            List<OpnameProduk> listOpnameProduk = ops.getByOpname(opname.getId());
            
            return new Response(true, null, "data berhasil disimpan", listOpnameProduk);
            
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal disimpan", null);
        }
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") Integer id){
        try {
            
            ops.batchDeleteByOpname(id);
            
            List<OpnameProduk> listOpnameProduk = ops.getByOpname(id);
            
            return new Response(true, null, os.delete(id) , listOpnameProduk);
            
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal dihapus", null);
        }
    }
    
    
    @RequestMapping(path = "/export/{id}", method = RequestMethod.GET)
    public Response export(@PathVariable("id") Integer id)throws Exception{
        
        try {
            
            List<OpnameProduk> listOpnameProduk = ops.getByOpname(id);
            
            if(listOpnameProduk != null){
                ops.export(id);
                return new Response(true, null, "data berhasil di export" , listOpnameProduk);
            }else{
                return new Response(false, "tidak ada data", "tidak ada data produk yang ditampilkan" , listOpnameProduk);
            }
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal diexport", null);
        }
    }
    
    @RequestMapping(path = "/upload/{id}/{nama:.+}", method = RequestMethod.GET)
    public Response upload(@PathVariable("id") Integer id , @PathVariable("nama") String nama)throws Exception{
        try {
            
            Opname opname = os.getById(id);
            
            if(opname != null){
                ops.upload(id, nama);
                List<OpnameProduk> listOpnameProduk = ops.getByOpname(id);
                return new Response(true, null, "data berhasil disimpan" , listOpnameProduk);
            }else{
                return new Response(false, "tidak ada data", "tidak ada data produk yang ditampilkan" , null);
            }
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal diupload", null);
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public Response update(@RequestBody Opname opname){
        try {
            List<Opname> opnames = (List<Opname>) os.update(opname);
            
            return new Response(true, null, "data berhasil diupdate" , opnames);
            
                
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal dihapus", null);
        }
    }
}
