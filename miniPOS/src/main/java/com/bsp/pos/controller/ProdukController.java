/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bsp.pos.model.Produk;
import com.bsp.pos.model.ProdukSuspend;
import com.bsp.pos.model.Suspend;
import com.bsp.pos.proxy.ProdukProxy;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.KategoriService;
import com.bsp.pos.service.MerekService;
import com.bsp.pos.service.ProdukPenjualanService;
import com.bsp.pos.service.ProdukService;
import com.bsp.pos.service.ProdukSuspendService;
import com.bsp.pos.service.SuspendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 * @author BSP
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/produk")
@Api(value = "ProdukController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Produk.class)})
public class ProdukController {
	ProdukPenjualanService pps = new ProdukPenjualanService();
	ProdukService ps = new ProdukService();
	SuspendService ss = new SuspendService();
	ProdukSuspendService pss = new ProdukSuspendService();
	MerekService ms = new MerekService();
	KategoriService ks = new KategoriService();

	@RequestMapping(method = RequestMethod.GET)
	public Response getAll(){
		try {
			List<Produk> produk = ps.getAll();
			if (produk != null && produk.size() > 0) {
				return new Response(true,null,"data berhasil ditampilakan",produk);
			} else {			
				return new Response(false,"tidak ada data","tidak ada data yang ditampilkan",null);
			}
		}catch(Exception e) {
			return new Response(false,e.getMessage().toString(),"data gagal ditampilkan",null);
		}
	}
	
	@RequestMapping(path = "/{id}",method = RequestMethod.GET)
	public Produk getById(@PathVariable("id") Integer id){
		return ps.getById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Produk add(@RequestBody ProdukProxy produk) {
		File currentDirFile = new File("");
		String helper = currentDirFile.getAbsolutePath();
		String path = helper + "\\img\\";
		Produk pp = new Produk();
		pp.setKategori(ks.getById(produk.getKategori_id()));
		pp.setMerek(ms.getMerekById(produk.getMerek_id()));
		pp.setId(produk.getProduk_id());
		pp.setNama(produk.getNama());
		pp.setTipe(produk.getTipe());
		pp.setKuantitas(produk.getKuantitas());
		pp.setHarga(produk.getHarga());
		pp.setStatus(produk.getStatus());
		pp.setCreated_by(produk.getCreated_by());
		pp.setCreated_dt(produk.getCreated_dt());
		pp.setUpdated_by(produk.getUpdated_by());
		pp.setUpdated_dt(produk.getUpdated_dt());
		pp.setImage(path + produk.getImage());
		pp.setStok(produk.getStok());
			
		return ps.save(pp);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		pps.batchDeleteByProduk(id);
		return ps.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestBody Produk produk){
		return ps.update(produk);
	}

	@RequestMapping(path = "/merek/{id}",method = RequestMethod.GET)
	public Response getAllByMerekId(@PathVariable("id") Integer id){
		try {
			List<Produk> model = ps.getAllByMerekId(id);
			List<Produk>  produk = new ArrayList<Produk>();
			for(Produk models : model){
				Produk dto = new Produk();
				dto.setId(models.getId());
				dto.setNama(models.getNama());
				dto.setHarga(models.getHarga());
				dto.setImage(models.getImage());
				dto.setKuantitas(models.getKuantitas());
				produk.add(dto);
			}
			if (produk != null && produk.size() > 0) {
				return new Response(true,null,"data berhasil ditampilakan",produk);
			} else {			
				return new Response(false,"tidak ada data","tidak ada data yang ditampilkan",null);
			}

		}catch(Exception e){
			return new Response(false,e.getMessage().toString(),"data gagal ditampilkan",null);
		}
	}
	
	@RequestMapping(path = "/kategori/{id}", method = RequestMethod.GET)
    public Response getAllByKategoriId(@PathVariable("id") Integer id) {
		try {
			List<Produk> model = ps.getAllByKategoriId(id);
			List<Produk>  produk = new ArrayList<Produk>();
			for(Produk models : model){
				Produk dto = new Produk();
				dto.setId(models.getId());
				dto.setNama(models.getNama());
				dto.setHarga(models.getHarga());
				dto.setImage(models.getImage());
				dto.setKuantitas(models.getKuantitas());
				produk.add(dto);
			}
			if (produk != null && produk.size() > 0) {
				return new Response(true,null,"data berhasil ditampilakan",produk);
			} else {			
				return new Response(false,"tidak ada data","tidak ada data yang ditampilkan",null);
			}

		}catch(Exception e){
			return new Response(false,e.getMessage().toString(),"data gagal ditampilkan",null);
		}
    }
}

