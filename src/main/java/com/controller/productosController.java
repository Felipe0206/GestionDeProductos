package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Producto;
import com.service.ProductoServece;


@RestController
@RequestMapping("/productos")
public class productosController {
    
    @Autowired
    private ProductoServece productoServece;

    @GetMapping()
    public List<Producto> obtenerTodosLosProductos(){
        return productoServece.obtenerTProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto>obtenerProductoPorId(@PathVariable Long id){
        return ResponseEntity.ok(productoServece.obteneProductoPorID(id));

    }

    @PostMapping
    public ResponseEntity<Producto> creaResponseEntity(@RequestBody Producto producto){
        Producto productoCreado= productoServece.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,@RequestBody Producto detallesProducto){

        Producto productoActualizado=productoServece.actualizarunProducto(id, detallesProducto);
        return ResponseEntity.ok(productoActualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoServece.eliminarproducto(id);
        return ResponseEntity.noContent().build();
    }
}
