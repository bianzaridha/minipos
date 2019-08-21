package com.bsp.pos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsp.pos.model.Produk;
import com.bsp.pos.model.ProdukSuspend;
import com.bsp.pos.model.Suspend;
import com.bsp.pos.proxy.ProdukSuspendProxy;
import com.bsp.pos.response.Response;
import com.bsp.pos.service.ProdukSuspendService;
import com.bsp.pos.service.SuspendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/produkSuspend")
@Api(value = "ProdukController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Produk.class)})
public class ProdukSuspendController {
	SuspendService ss = new SuspendService();
	ProdukSuspendService pss = new ProdukSuspendService();
	
	@RequestMapping(method = RequestMethod.POST)
	public Response pushSuspend(@RequestBody ProdukSuspendProxy psp) {
		try {
			Suspend model = new Suspend();
			model.setNama(psp.getNama());
			Suspend suspend = ss.save(model);
			for(Produk produks : psp.getProduk()) {
				ProdukSuspend dto = new ProdukSuspend(); 
				dto.setSuspend(suspend);
				dto.setProduk(produks);
				dto.setKuantitas(produks.getKuantitas());
				pss.save(dto);
			}
			return new Response(true,null,"data berhasil ditambahkan",null);
		}catch(Exception e) {
			return new Response(false,e.getMessage().toString(),"data gagal ditambahkan",null);
		}
	}

	
	@RequestMapping(path = "/{id}",method = RequestMethod.GET)
	public Response getByIdSuspend(@PathVariable("id") Integer id) {
		try {
			List<ProdukSuspend> produkSuspend =  pss.getByIdSuspend(id);
			String nama = ss.getById(id).getNama();
			List<ProdukSuspendProxy> list = new ArrayList<ProdukSuspendProxy>();
			
			List<Produk> produk = new ArrayList<Produk>();
			for(ProdukSuspend data : produkSuspend) {
				Produk dto = new Produk();
				dto.setId(data.getProduk().getId());
				dto.setHarga(data.getProduk().getHarga());
				dto.setNama(data.getProduk().getNama());
				dto.setKuantitas(data.getKuantitas());
				produk.add(dto);
			}
			ProdukSuspendProxy psp = new ProdukSuspendProxy();
			psp.setId(id);
			psp.setNama(nama);
			psp.setProduk(produk);
			list.add(psp);
			
			if (produkSuspend != null && produkSuspend.size() > 0) {
				return new Response(true,null,"data berhasil ditampilakan",list);
			} else {			
				return new Response(false,"tidak ada data","tidak ada data yang ditampilkan",null);
			}
		}catch(Exception e) {
			return new Response(false,e.getMessage().toString(),"data gagal ditampilkan",null);
		}
	}
	
//	@RequestMapping(path = "/allSuspend",method = RequestMethod.GET)
//	public ResponseImpl getSuspend() {
//		try {
//			
//			List<Suspend> suspend = ss.getAll();
//			List<ProdukSuspendProxy> list = new ArrayList<ProdukSuspendProxy>();
//			
//			for(Suspend suspends:suspend) {
//				ProdukSuspendProxy psp = new ProdukSuspendProxy();
//				List<ProdukSuspend> produkSuspend =  pss.getByIdSuspend(suspends.getId());
//				List<Produk> produk = new ArrayList<Produk>();
//				for(ProdukSuspend data : produkSuspend) {
//					Produk dto = new Produk();
//					dto.setId(data.getProduk().getId());
//					dto.setHarga(data.getProduk().getHarga());
//					dto.setNama(data.getProduk().getNama());
//					dto.setKuantitas(data.getKuantitas());
//					produk.add(dto);
//				}
//				psp.setId(suspends.getId());
//				psp.setNama(suspends.getNama());
//				psp.setProduk(produk);
//				list.add(psp);
//			}
//			if (list != null && list.size() > 0) {
//				responImpl.setResponse(new Response(true,null,"data berhasil ditampilakan",list));
//				return  responImpl;
//			} else {			
//				responImpl.setResponse(new Response(false,"tidak ada data","tidak ada data yang ditampilkan",null));
//				return responImpl;
//			}
//		}catch(Exception e) {
//			responImpl.setResponse( new Response(false,e.getMessage().toString(),"data gagal ditampilkan",null));
//			return responImpl;
//		}
//	}
}
