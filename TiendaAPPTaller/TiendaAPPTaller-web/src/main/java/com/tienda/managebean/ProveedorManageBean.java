/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import com.tienda.entidades.Proveedor;
import com.tienda.session.ProveedorFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author danny
 */
@Named(value = "proveedorManageBean")
@ViewScoped
public class ProveedorManageBean implements Serializable, ManagedBeanInterface<Proveedor>{

    /**
     * Creates a new instance of ProveedorManageBean
     */
    @EJB 
    private ProveedorFacadeLocal proveedorFacadeLocal;
    
    private List<Proveedor> listaProveedor;
    
    private Proveedor proveedor;
    
    public ProveedorManageBean() {
    }
    
    @PostConstruct
    public void init(){
        listaProveedor=proveedorFacadeLocal.findAll();
    }

    @Override
    public void nuevo() {
        proveedor = new Proveedor();
        /*try {
            if(proveedor.getCodigo() == null){
                proveedorFacadeLocal.create(proveedor);
            }else{
                proveedorFacadeLocal.edit(proveedor);
            }
            proveedor = null;
            listaProveedor = proveedorFacadeLocal.findAll();
            mostrarMesajeTry("INFORMACION EXITOSA", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMesajeTry("INFORMACION EXITOSA", FacesMessage.SEVERITY_ERROR);
        }*/
    }

    @Override
    public void grabar() {
        try {
            if (proveedor.getCodigo()==null){
                proveedorFacadeLocal.create(proveedor);
            }else /*if(cargo.getCodigo()!= null && cargo == null){*/{
                proveedorFacadeLocal.edit(proveedor);
            }
            proveedor = null;
            listaProveedor=proveedorFacadeLocal.findAll();
            mostrarMesajeTry("INFORMACION OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Proveedor t) {
        proveedor = t;
    }

    @Override
    public void eliminar(Proveedor t) {
        try {
            proveedorFacadeLocal.remove(t);
            listaProveedor = proveedorFacadeLocal.findAll();
            mostrarMesajeTry("ELEMINADO EXITOSAMENTE", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        listaProveedor = proveedorFacadeLocal.findAll();
        proveedor = null;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
       
    
}
