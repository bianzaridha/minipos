/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.controller;

import com.bsp.pos.model.Adjustment;
import com.bsp.pos.model.AdjustmentProduk;
import com.bsp.pos.model.Produk;
import com.bsp.pos.proxy.AProdukProxy;
import com.bsp.pos.proxy.AdjustmentProdukProxy;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.AdjustmentService;
import com.bsp.pos.service.AdjustmentProdukService;
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
@RequestMapping(path = "/adjustmentproduk")
@Api(value = "AdjustmentProdukController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = AdjustmentProduk.class)})
public class AdjustmentProdukController {
    
    AdjustmentProdukService aps = new AdjustmentProdukService();
    AdjustmentService as = new AdjustmentService();
    ProdukService prs = new ProdukService();
    
    @RequestMapping(method = RequestMethod.GET)
    public Response getAll(){
        try {
            
            List<AdjustmentProduk> adjustmentProduk = aps.getAll();
            if(adjustmentProduk != null){
                return new Response(true, null, "data berhasil ditampilkan", adjustmentProduk);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", adjustmentProduk);
            }
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
    @RequestMapping(path = "/produk/{id}",method = RequestMethod.GET)
    public Response getByProduk(@PathVariable("id") Integer id){
        try {
            
            List<AdjustmentProduk> adjustmentProduk = aps.getByProduk(id);
            if(adjustmentProduk != null){
                return new Response(true, null, "data berhasil ditampilkan", adjustmentProduk);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", adjustmentProduk);
            }
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
    @RequestMapping(path = "/adjustment/{id}",method = RequestMethod.GET)
    public Response getByAdjustment(@PathVariable("id") Integer id){
        try {
            
            List<AdjustmentProduk> adjustmentProduk = aps.getByAdjustment(id);
            if(adjustmentProduk != null){
                return new Response(true, null, "data berhasil ditampilkan", adjustmentProduk);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", adjustmentProduk);
            }
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Response add(@RequestBody AdjustmentProdukProxy adjustmentprodukProxy){
        try {
            Adjustment adjustment = as.save(adjustmentprodukProxy.getAdjustment());
        
            ArrayList<AdjustmentProduk> returnList = new ArrayList<AdjustmentProduk>();

            for(AProdukProxy id_produk : adjustmentprodukProxy.getProduks()){
                Produk produk = prs.getById(id_produk.getId_produk());

                if(id_produk.getJenis().equals("1")){
                    produk.setKuantitas(produk.getKuantitas() + id_produk.getKuantitas());
                }else{
                    produk.setKuantitas(produk.getKuantitas() - id_produk.getKuantitas());
                }
                prs.update(produk);
                AdjustmentProduk adjustmentproduk = new AdjustmentProduk();
                adjustmentproduk.setAdjustment(adjustment);
                adjustmentproduk.setJenis(id_produk.getJenis());
                adjustmentproduk.setKuantitas(id_produk.getKuantitas());
                adjustmentproduk.setProduk(produk);


                aps.save(adjustmentproduk);
                returnList.add(adjustmentproduk);
            }
            
            if(returnList != null){
                return new Response(true, null, "data berhasil ditampilkan", returnList);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", returnList);
            }
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") Integer id){
        try {
            
            List<AdjustmentProduk> adjustmentProduk = aps.getByAdjustment(id);
            if(adjustmentProduk != null){
                aps.batchDeleteByAdjustment(id);
                adjustmentProduk = aps.getAll();
                return new Response(true, null, "data berhasil dihapus", adjustmentProduk);
            }else{
                return new Response(false,"tidak ada data","tidak ada data yang ditampilkan", adjustmentProduk);
            }
        } catch (Exception e) {
            return new Response(false, e.getMessage().toString(), "data gagal ditampilkan", null);
        }
    }
    
}
