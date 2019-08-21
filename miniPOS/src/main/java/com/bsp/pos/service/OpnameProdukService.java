/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.OpnameProduk;
import com.bsp.pos.proxy.DetailOpnameProxy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;

/**
 *
 * @author BSP
 */
public class OpnameProdukService {
    public OpnameProduk save(OpnameProduk opnameproduk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(opnameproduk);
        session.getTransaction().commit();
        session.close();
        
        return opnameproduk;
    }
    
     public OpnameProduk update(OpnameProduk opnameproduk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(opnameproduk);
        session.getTransaction().commit();
        session.close();
        
        return opnameproduk;
    }
    
    public List<OpnameProduk> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from OpnameProduk";
        List<OpnameProduk> opnameproduk = (List<OpnameProduk>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return opnameproduk;
    }
    
    public List<OpnameProduk> getByProduk(Integer id){
        String query = "from OpnameProduk where id_produk=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<OpnameProduk> opnameproduk = (List<OpnameProduk>) session.createQuery(
                                        query).setParameter("id", id).list();
        
        session.getTransaction().commit();
        session.close();
        
        return opnameproduk;
    }
    
    public List<OpnameProduk> getByOpname(Integer id){
        String query = "from OpnameProduk where id_opname=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<OpnameProduk> opnameproduk = (List<OpnameProduk>) session.createQuery(
                                        query).setParameter("id", id).list();
        
        session.getTransaction().commit();
        session.close();
        
        return opnameproduk;
    }
    
    
    public String delete(OpnameProduk opnameproduk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.delete(opnameproduk);
        
        session.getTransaction().commit();
        session.close();
        
        return "deleted";
    }
    
    public void batchDeleteByProduk (Integer id){
        List<OpnameProduk> list = this.getByProduk(id);
        for (OpnameProduk kill : list) {
          this.delete(kill);
        }
    }
    
    public void batchDeleteByOpname (Integer id){
        List<OpnameProduk> list = this.getByOpname(id);
        for (OpnameProduk kill : list) {
          this.delete(kill);
        }
    }
    
    
    public String export(Integer id) throws Exception{
        
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("OpnameProduk Db");
        XSSFRow row=spreadsheet.createRow(0);
        XSSFCell cell;
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop((short) 6); 
        style.setBorderBottom((short) 1);
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);   
        
        cell = row.createCell(0);
        cell.setCellValue("Id");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("Nama");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("Seharusnya");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("Terhitung");
        cell.setCellStyle(style);
        
        List<OpnameProduk> dataOpnameProduk = this.getByOpname(id);
        int i = 1;
        for(OpnameProduk opnameProduk : dataOpnameProduk){
            row = spreadsheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(opnameProduk.getProduk().getId());
            cell = row.createCell(1);
            cell.setCellValue(opnameProduk.getProduk().getNama());
            cell = row.createCell(2);
            cell.setCellValue(opnameProduk.getProduk().getKuantitas());
            cell = row.createCell(3);
            if(opnameProduk.getTerhitung() == null){
                Integer terhitung = 0;
                cell.setCellValue(terhitung);
            }else{
                cell.setCellValue(opnameProduk.getTerhitung());
            }
            i++;
        }
        FileOutputStream out = new FileOutputStream(new File("output.xlsx"));
        workbook.write(out);
        out.close();
        
        return "exported";
    }
    
    public ArrayList<OpnameProduk> upload(Integer id, String fileName) throws Exception{

        ArrayList<OpnameProduk> returnList = new ArrayList<OpnameProduk>();
        
        DataFormatter formatter = new DataFormatter();
        File currentDirFile = new File("");
        String path = currentDirFile.getAbsolutePath();
        File file =  new File(path+"\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if(fileExtensionName.equals(".xlsx")){
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            XSSFRow row;
            for (int i = 1; i <= spreadsheet.getLastRowNum(); i++) {
                row = spreadsheet.getRow(i);
                
                
                Integer id_produk = (int) row.getCell(0).getNumericCellValue();
                String nama_produk = row.getCell(1).getStringCellValue();
                Integer seharusnya = (int) row.getCell(2).getNumericCellValue();
                Integer terhitung = (int) row.getCell(3).getNumericCellValue();
                
                System.out.println(terhitung);
                
                List<OpnameProduk> listOpnameProduk = this.getByOpname(id);
                
                for(OpnameProduk opnameproduk : listOpnameProduk){
                    if(opnameproduk.getProduk().getId() == id_produk){
                        opnameproduk.setTerhitung(terhitung);
                        this.update(opnameproduk);
                        returnList.add(opnameproduk);
                    }
                }
                
            }
        }else if(fileExtensionName.equals(".xls")){
            Workbook workbook = new HSSFWorkbook(inputStream);
        }
        
        
        return returnList;
    }
    
    public List<DetailOpnameProxy> detail(Integer id){
        List<OpnameProduk> listOpnameProduk = this.getByOpname(id);
        
        List<DetailOpnameProxy> returnList = new ArrayList<DetailOpnameProxy>();
    
        int no = 1;
        for(OpnameProduk opnameproduk : listOpnameProduk){
            DetailOpnameProxy doProxy = new DetailOpnameProxy();
            doProxy.setNo(no);
            doProxy.setDeskripsi(opnameproduk.getProduk().getId().toString() + " - " + opnameproduk.getProduk().getNama());
            doProxy.setSeharusnya(opnameproduk.getProduk().getKuantitas());
            doProxy.setTerhitung(opnameproduk.getTerhitung());
            doProxy.setPerbedaan(opnameproduk.getProduk().getKuantitas() - opnameproduk.getTerhitung());

            returnList.add(doProxy);
            no++;
        }
        
        return returnList;
        
    }
}
