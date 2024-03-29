package com.curso.ecomerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController implements ProductoService{
	
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