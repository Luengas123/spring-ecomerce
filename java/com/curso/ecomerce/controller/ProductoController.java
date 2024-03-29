package com.curso.ecomerce.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.UploadFileService;
import com.curso.ecommerce.service.UsuarioServiceImpl;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger LOOGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;

	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto) {
		LOOGER.info("Este es el objeto producto {}", producto);
		Usuario u=new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(u);		
		productoService.save(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/edit/({id}")
	public String edit(@PathVariable Integer id) {
		Producto producto= new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto= optionalProducto.get();
		LOOGER.info("Producto buscado: {}",producto);
		return "productos/edit";
	}
	
}	
