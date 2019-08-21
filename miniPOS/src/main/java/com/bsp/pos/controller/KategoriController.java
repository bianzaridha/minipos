package com.bsp.pos.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsp.pos.model.Kategori;
import com.bsp.pos.model.Merek;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.KategoriService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/kategori")
@Api(value = "KategoriController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Kategori.class)})
public class KategoriController {
	KategoriService ks = new KategoriService();

	@RequestMapping(method = RequestMethod.GET)
	public Response getAll(){
		try {
			List<Kategori> kategori = ks.getAll();
			if (kategori != null && kategori.size() > 0) {
				return new Response(true,null,"data berhasil ditampilakan",kategori);
			} else {			
				return new Response(false,"tidak ada data","tidak ada data yang ditampilkan",null);
			}
		}catch(Exception e) {
			return new Response(false,e.getMessage().toString(),"data gagal ditampilkan",null);
		}
	}

	@RequestMapping(path = "/{id}",method = RequestMethod.GET)
	public Kategori getById(@PathVariable("id") Integer id){
		return ks.getById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Kategori add(@RequestBody Kategori kategori){
		return ks.save(kategori);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		return ks.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestBody Kategori kategori){
		return ks.update(kategori);
	}
}
