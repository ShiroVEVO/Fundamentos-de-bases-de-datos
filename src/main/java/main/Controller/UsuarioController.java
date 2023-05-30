package main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.Model.Usuario;
import main.Service.UsuarioService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() throws SQLException { // FUNCIONAL
        ResponseEntity response;
        if (usuarioService.listarUsuarios() == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(usuarioService.listarUsuarios(), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarUsuario(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response = null;
        if (usuarioService.listarUsuario(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(usuarioService.listarUsuario(id), HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/guardar")
    public ResponseEntity guardarUsuario(@RequestBody Usuario usuario) throws SQLException {
        ResponseEntity response;
        if (usuarioService.listarUsuario(usuario.getIdentificacion()) != null) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response = new ResponseEntity(usuarioService.guardarUsuario(usuario), HttpStatus.OK);
        }
        return response;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrarUsuario(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response;
        if (usuarioService.listarUsuario(id) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            usuarioService.eliminarUsuario(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario)
            throws SQLException {
        ResponseEntity response = null;
        if (usuarioService.listarUsuario(usuario.getIdentificacion()) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            response = new ResponseEntity(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
        }
        return response;
    }

}
