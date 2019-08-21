/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.controller;

import com.bsp.pos.model.Penjualan;
import com.bsp.pos.proxy.PenjualanProxy;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.PenjualanService;
import com.bsp.pos.service.ProdukPenjualanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
@RequestMapping(path = "/penjualan")
@Api(value = "PenjualanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Penjualan.class)})
public class PenjualanController {
    ProdukPenjualanService pps = new ProdukPenjualanService();
    PenjualanService ps = new PenjualanService();
    ProdukPenjualanController ppc = new ProdukPenjualanController();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Penjualan> getAll(){
        return ps.getAll();
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Penjualan getById(@PathVariable("id") Integer id){
        return ps.getById(id);
    }
    
    @RequestMapping(path = "/export/{nama}",method = RequestMethod.GET)
    public String export(@PathVariable("nama") String nama) throws Exception{
        return ps.export(nama);
    }
    
    @RequestMapping(path = "/upload/{nama:.+}",method = RequestMethod.GET)
    public ArrayList<Penjualan> upload(@PathVariable("nama") String nama) throws Exception{
        return ps.upload(nama);
    }
    
    @RequestMapping(method = RequestMethod.POST,headers = "Accept=application/json")
    public Response add(@RequestBody PenjualanProxy penjualanProxy){
    	Penjualan penjualan = penjualanProxy.getPenjualan();
    	penjualan.setPenjualan_by("Walk in customer");
    	penjualan.setPenjualan_biaya_pengiriman(0);
    	penjualan.setPenjualan_grand_total(0);
    	penjualan.setPenjualan_diskon(0);
    	penjualan.setPenjualan_kembali(0);
    	penjualan.setPenjualan_noref("");
    	penjualan.setPenjualan_total(0);
    	penjualan.setPenjualan_status(0);
    	penjualan.setCreated_by("");
    	penjualan.setUpdated_by("");
    	
    	Calendar currenttime = Calendar.getInstance();
    	Date date = new Date((currenttime.getTime()).getTime());  
    	int year = currenttime.get(Calendar.YEAR);
    	int month = currenttime.get(Calendar.MONTH)+1;
    	
    	penjualan.setPenjualan_dt(date);
    	penjualan.setCreated_dt(date);
    	penjualan.setUpdated_dt(date);
        ps.save(penjualan);
        long count = ps.getCountPenyjualanByMonth(year,month);
        String noref = null;
        if(count<10) {
        	 noref = "POS/"+year +"/"+ month +"/00"+count;
        }else if(count<100){
        	 noref = "POS/"+year +"/"+ month+"/0"+count;
        }else {
        	 noref = "POS/"+year +"/"+ month+"/"+count;
        }
    	
        Response res = ppc.add(penjualanProxy.getProdukpenjualanProxy(), penjualan,noref);
        return res;
        
        
   }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        pps.batchDeleteByPenjualan(id);
        return ps.delete(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Penjualan penjualan){
        return ps.update(penjualan);
    }
}
