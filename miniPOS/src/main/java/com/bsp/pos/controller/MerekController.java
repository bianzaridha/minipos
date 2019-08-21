package com.bsp.pos.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsp.pos.model.Merek;
import com.bsp.pos.model.Produk;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.MerekService;
import com.bsp.pos.service.ProdukPenjualanService;
import com.bsp.pos.service.ProdukService;
import com.bsp.pos.service.ProdukSuspendService;
import com.bsp.pos.service.SuspendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/merek")
@Api(value = "MerekController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Produk.class)})
public class MerekController {
	MerekService ms = new MerekService();
	
	@RequestMapping(method = RequestMethod.GET)
	public Response getAllMerek(){
		try {
			List<Merek> merek = ms.getAll();
			if (merek != null && merek.size() > 0) {
				return new Response(true,null,"data berhasil ditampilakan",merek);
			} else {			
				return new Response(false,"tidak ada data","tidak ada data yang ditampilkan",null);
			}
		}catch(Exception e) {
			return new Response(false,e.getMessage().toString(),"data gagal ditampilkan",null);
		}
	}
}
