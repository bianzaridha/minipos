/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.controller;

import com.bsp.pos.model.Penjualan;
import com.bsp.pos.model.ProdukPenjualan;
import com.bsp.pos.proxy.ProdukPenjualanProxy;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.PenjualanService;
import com.bsp.pos.service.ProdukPenjualanService;
import com.bsp.pos.service.ProdukService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
 * @author BSP
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/produkpenjualan")
@Api(value = "ProdukPenjualanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ProdukPenjualan.class)})
public class ProdukPenjualanController {
    
    ProdukPenjualanService pps = new ProdukPenjualanService();
    PenjualanService pns = new PenjualanService();
    ProdukService prs = new ProdukService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<ProdukPenjualan> getAll(){
        return pps.getAll();
    }
    
    @RequestMapping(path = "/produk/{id}",method = RequestMethod.GET)
    public List<ProdukPenjualan> getByProduk(@PathVariable("id") Integer id){
        return pps.getByProduk(id);
    }
    
    @RequestMapping(path = "/penjualan/{id}",method = RequestMethod.GET)
    public List<ProdukPenjualan> getByPenjualan(@PathVariable("id") Integer id){
        return pps.getByPenjualan(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Response add(List<ProdukPenjualanProxy> produkpenjualanProxy, Penjualan penjualan, String noref){
        try {
     	   int total = 0;
     	   int idPenjualan = 0;
 	    	   for(ProdukPenjualanProxy ppproxy : produkpenjualanProxy) {
 	    		 idPenjualan = ppproxy.getId_penjualan();
 	    		 int subtotal = ppproxy.getProduk_penjualan_kuantitas()*ppproxy.getProduk_penjualan_harga();
 	    	   		ProdukPenjualan pp = new ProdukPenjualan(
 	                    prs.getById(ppproxy.getId_produk()),
 	                    penjualan,
 	                    ppproxy.getProduk_penjualan_kuantitas(),
 	                    ppproxy.getProduk_penjualan_harga(),
 	                    subtotal,
 	                    ppproxy.getProduk_penjualan_note());
 	    	   	  pps.save(pp);                             
 	    	   	  total += subtotal;
 	    	   	  
 	    	   }
     	   penjualan.setPenjualan_total(total);
     	   penjualan.setPenjualan_grand_total(total-penjualan.getPenjualan_diskon()+penjualan.getPenjualan_biaya_pengiriman());
     	   penjualan.setPenjualan_kembali(penjualan.getPenjualan_bayar()-penjualan.getPenjualan_grand_total());
     	   penjualan.setPenjualan_noref(noref);
     	   pns.update(penjualan);
     	   List<Penjualan> penjList = new ArrayList<Penjualan>();
     	   penjList.add(penjualan);
     	   return new Response(true,null,"Berhasil melakukan pembayaran",null);
     	}catch(Exception e) {
     		return new Response(false,e.getMessage().toString(),"Terjadi Kesalahan",null);
        }
        
        
        
     }
    
    @RequestMapping(path = "/produk/{id}", method = RequestMethod.DELETE)
    public String deleteByProduk(@PathVariable("id") Integer id){
        pps.batchDeleteByProduk(id);
        return "deleted";
    }
    
    @RequestMapping(path = "/penjualan/{id}", method = RequestMethod.DELETE)
    public String deleteByPenjualan(@PathVariable("id") Integer id){
        pps.batchDeleteByPenjualan(id);
        return "deleted";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody ProdukPenjualanProxy produkpenjualanProxy){
        return pps.update(produkpenjualanProxy);
    }

}
