/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.proxy;

import com.bsp.pos.model.Adjustment;
import java.util.List;

/**
 *
 * @author Bianza
 */
public class AdjustmentProdukProxy {
    
    private Adjustment adjustment;
    private List<AProdukProxy> Produks;

    public Adjustment getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(Adjustment adjustment) {
        this.adjustment = adjustment;
    }

    public List<AProdukProxy> getProduks() {
        return Produks;
    }

    public void setProduks(List<AProdukProxy> Produks) {
        this.Produks = Produks;
    }
    
}
