/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Penjualan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
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
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author BSP
 */
public class PenjualanService {
    
    public Penjualan save(Penjualan penjualan){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.save(penjualan);
        session.getTransaction().commit();
        session.close();
        
        return penjualan;
    }
    
    public List<Penjualan> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from Penjualan";
        List<Penjualan> penjualan = (List<Penjualan>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return penjualan;
    }
    
    public Penjualan getById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Penjualan penjualan = (Penjualan) session.get(Penjualan.class, id);
        session.getTransaction().commit();
        session.close();
        
        return penjualan;
    }
    
    public String delete(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Penjualan penjualan = this.getById(id);
        session.delete(penjualan);
        
        session.getTransaction().commit();
        session.close();
        
        return "Penjualan with id " + id + " deleted";
    }
    
    public String update(Penjualan penjualan){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(penjualan);
        
        session.getTransaction().commit();
        session.close();
        
        return "Penjualan with id " + penjualan.getId() + " updated";
    }
    
    public String export(String nama) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("Penjualan Db");
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
        cell.setCellValue("No");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("Date");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("Penjualan By");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("Penjualan Noref");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("Penjualan Biaya Pengiriman");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("Penjualan Status");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("Penjualan Total");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("Penjualan Grand Total");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("Penjualan Diskon");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("Penjualan Bayar");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("Penjualan Kembali");
        cell.setCellStyle(style);
        
        List<Penjualan> dataPenjualan = this.getAll();
        
        int i = 1;
        int no = 1;
        for(Penjualan penjualan : dataPenjualan){
            row = spreadsheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(no);
            cell = row.createCell(1);
            cell.setCellValue(sdf.format(penjualan.getPenjualan_dt()));
            cell = row.createCell(2);
            cell.setCellValue(penjualan.getPenjualan_by());
            cell = row.createCell(3);
            cell.setCellValue(penjualan.getPenjualan_noref());
            cell = row.createCell(4);
            cell.setCellValue(penjualan.getPenjualan_biaya_pengiriman());
            cell = row.createCell(5);
            cell.setCellValue(penjualan.getPenjualan_status());
            cell = row.createCell(6);
            cell.setCellValue(penjualan.getPenjualan_total());
            cell = row.createCell(7);
            cell.setCellValue(penjualan.getPenjualan_grand_total());
            cell = row.createCell(8);
            cell.setCellValue(penjualan.getPenjualan_diskon());
            cell = row.createCell(9);
            cell.setCellValue(penjualan.getPenjualan_bayar());
            cell = row.createCell(10);
            cell.setCellValue(penjualan.getPenjualan_kembali());
            i++;
            no++;
        }
        FileOutputStream out = new FileOutputStream(new File(nama + ".xlsx"));
        workbook.write(out);
        out.close();
        
        return "exported";
    }
    
    public ArrayList<Penjualan> upload(String fileName) throws Exception{
        Penjualan penjualan = new Penjualan();
        ArrayList<Penjualan> listPenjualan = new ArrayList<Penjualan>();
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DataFormatter formatter = new DataFormatter();
        File file =  new File("C:\\Users\\BSP\\Documents\\Data Bianza\\minipos\\miniPOS\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if(fileExtensionName.equals(".xlsx")){
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            XSSFRow row;
            for (int i = 1; i <= spreadsheet.getLastRowNum(); i++) {
                row = spreadsheet.getRow(i);
               // Date penjualan_dt = Date.valueOf(formatter.formatCellValue(row.getCell(1))); //error
                
                Date penjualan_dt = Date.valueOf("2015-03-31");
                String created_by = "admin";
                Date created_dt = new Date(System.currentTimeMillis());
                
                penjualan.setPenjualan_dt(penjualan_dt);
                penjualan.setPenjualan_by(row.getCell(2).getStringCellValue());
                penjualan.setPenjualan_noref(row.getCell(3).getStringCellValue());
                penjualan.setPenjualan_biaya_pengiriman((int)row.getCell(4).getNumericCellValue());
                penjualan.setPenjualan_status((int)row.getCell(5).getNumericCellValue());
                penjualan.setPenjualan_total((int)row.getCell(6).getNumericCellValue());
                penjualan.setPenjualan_grand_total((int)row.getCell(7).getNumericCellValue());
                penjualan.setPenjualan_diskon((int)row.getCell(8).getNumericCellValue());
                penjualan.setPenjualan_bayar((int)row.getCell(9).getNumericCellValue());
                penjualan.setPenjualan_kembali((int)row.getCell(10).getNumericCellValue());
                penjualan.setCreated_by(created_by);
                penjualan.setCreated_dt(created_dt);
                
                this.save(penjualan);
                listPenjualan.add(penjualan);
                
            }
        }else if(fileExtensionName.equals(".xls")){
            Workbook workbook = new HSSFWorkbook(inputStream);
        }
        
        
        return listPenjualan;
    }

	public long getCountPenyjualanByMonth(int year,int month) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        Query query = session.createQuery("Select Count(id) from Penjualan pen where YEAR(penjualan_dt) = :y AND MONTH(penjualan_dt) = :m");
        query.setParameter("y", year);
        query.setParameter("m", month);
        List<Long> list = query.list();  
        long count = list.get(0);
        
        session.getTransaction().commit();
        session.close();
		return count;
	}
}
