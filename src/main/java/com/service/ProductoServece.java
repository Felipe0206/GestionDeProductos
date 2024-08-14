package com.service;

import com.model.Producto;
import com.repository.productoRepository;
import com.exeption.ResorseNotFoundExeption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServece {
    
    @Autowired
    private ProductoRepository  productoRepository;

    //Obtener todos los produuctos

    public List<Producto> obtenerTProductos(){
        return productoRepository.findAll();
    }

    //Obtener producto por su ID 
    public Producto obteneProductoPorID(Long id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResorseNotFoundExeption("producto no encontrado con el id :" + id));
    }

    //crear un nuevo producto
    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    //Actualizar un producto existente 
    public Producto actualizarunProducto(Long id,Producto detallesProducto){

        Producto producto= obteneProductoPorID(id);
        producto.setNombre(detallesProducto.getNombre());
        producto.setDescripcion(detallesProducto.getDescripcion());
        producto.setPrecio(detallesProducto.getPrecio());
        producto.setStock(detallesProducto.getStock());
        return productoRepository.save(producto);
    }

    //Eliminar un producto
    public void eliminarproducto(long id){
        Producto  producto =obteneProductoPorID(id);
        productoRepository.delete(producto);
    }
}
