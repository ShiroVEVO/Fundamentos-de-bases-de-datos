window.onload = function(){
fetch("/odontologo")
.then(function(response){
    return response.json();})
.then(function(data){
    let tablaOdontologos = document.querySelector("#BodyTablaOdontologos");
    for(let i = 0; i < data.length; i++){
        tablaOdontologos.innerHTML +=
        "<tr id=\"registroOdontologoKey" + data[i].matricula + "\">" +
            "<th>" + data[i].matricula + "</th>" +
            "<th>" + data[i].user + "</th>" +
            "<th>" + data[i].password + "</th>" +
            "<th>" + data[i].nombre + "</th>" +
            "<th>" + data[i].apellido + "</th>" +
            "<th><input type=\"button\" class=\"btn btn-primary btnActualizarOdontologo\" id=\"" + data[i].matricula + "\" value=\"✏\"></input></th>" +
            "<th><input type=\"button\" class=\"btn btn-danger btnEliminarOdontologo\" id=\"" + data[i].matricula + "\" value=\"❌\"></input></th>" +
        "</tr>";
    }
    let botonesEliminarOdontologo = document.querySelectorAll('.btnEliminarOdontologo');
    for(let i = 0; i < botonesEliminarOdontologo.length; i++){
        botonesEliminarOdontologo[i].addEventListener("click",function(){
            borrarOdontologo(botonesEliminarOdontologo[i].attributes[2].value);
        });
    }
    let botonesActualizarOdontologo = document.querySelectorAll('.btnActualizarOdontologo');
        for(let i = 0; i < botonesActualizarOdontologo.length; i++){
            botonesActualizarOdontologo[i].addEventListener("click",function(){
                actualizarOdontologo(botonesActualizarOdontologo[i].attributes[2].value);
            });
        }
})

fetch("/paciente")
.then(function(response){
    return response.json();})
.then(function(data){
    let tablaPacientes = document.querySelector("#BodyTablaPacientes");
    for(let i = 0; i < data.length; i++){
        tablaPacientes.innerHTML +=
        "<tr id=\"registroPacienteKey" + data[i].DNI + "\">" +
            "<th>" + data[i].DNI + "</th>" +
            "<th>" + data[i].user + "</th>" +
            "<th>" + data[i].password + "</th>" +
            "<th>" + data[i].nombre + "</th>" +
            "<th>" + data[i].apellido + "</th>" +
            "<th>" + data[i].domicilio + "</th>" +
            "<th>" + data[i].fechaAlta + "</th>" +
            "<th><input type=\"button\" class=\"btn btn-primary btnActualizarPaciente\" id=\"" + data[i].DNI + "\" value=\"✏\"></input></th>" +
            "<th><input type=\"button\" class=\"btn btn-danger btnEliminarPaciente\" id=\"" + data[i].DNI + "\" value=\"❌\"></input></th>" +
        "</tr>";
    }
    let botonesEliminarPacientes = document.querySelectorAll('.btnEliminarPaciente');
    for(let i = 0; i < botonesEliminarPacientes.length; i++){
        botonesEliminarPacientes[i].addEventListener("click",function(){
            borrarPaciente(botonesEliminarPacientes[i].attributes[2].value);
        });
    }
    let botonesActualizarPaciente = document.querySelectorAll('.btnActualizarPaciente');
        for(let i = 0; i < botonesActualizarPaciente.length; i++){
            botonesActualizarPaciente[i].addEventListener("click",function(){
                actualizarPaciente(botonesActualizarPaciente[i].attributes[2].value);
            });
        }
})
}
