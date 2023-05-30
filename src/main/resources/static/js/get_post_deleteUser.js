window.onload = function(){
fetch("/turno")
.then(function(response){
    return response.json();})
.then(function(data){
        let tablaTurno = document.querySelector("#BodyTablaTurnos");
        for(let i = 0; i < data.length; i++){
            tablaTurno.innerHTML +=
            "<tr id=\"registroTurnoKey" + data[i].numTurno + "\">" +
                "<th>" + data[i].numTurno + "</th>" +
                "<th>" + data[i].hora + "</th>" +
                "<th>" + data[i].fecha + "</th>" +
                "<th>" + data[i].matricula + "</th>" +
                "<th>" + data[i].nombreOdontologo + "</th>" +
                "<th>" + data[i].DNI + "</th>" +
                "<th>" + data[i].nombrePaciente + "</th>" +
                "<th><input type=\"button\" class=\"btn btn-danger btnEliminarTurno\" id=\"" + data[i].numTurno + "\" value=\"❌\"></input></th>" +
            "</tr>";
        }
        let botonesEliminarTurno = document.querySelectorAll('.btnEliminarTurno');
        for(let i = 0; i < botonesEliminarTurno.length; i++){
            botonesEliminarTurno[i].addEventListener("click",function(){
                borrarTurno(botonesEliminarTurno[i].attributes[2].value);
            });
        }
    })
    //POST----------------------------
        let btnGuardar = document.querySelector("#guardar");
        let data;
        let url;
        btnGuardar.addEventListener("click",function(e){
            e.preventDefault();
            let form = document.querySelector(".sectionIngreso form");
            let organizador = [];
            for(let i = 0; i< form.length-1; i++){
                organizador.push(form[i].value);
            }
            console.log(organizador);
            data = {
                numTurno: organizador[0],
                hora: organizador[1],
                fecha: organizador[2],
                matricula: organizador[3],
                DNI: organizador[4]
            }
                url = '/turno/guardar';
            let settings = {
                method:'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(data)
            };

            fetch(url,settings)
            .then(function(response){
                return response.json();
            })
            .then(function(data){
                alert("guardado con exito");
                location.reload();
            })
        })
}
function borrarTurno(numTurno){
    let url = '/turno/borrar/' + numTurno;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
    .then(function(response){
        location.reload();
        return response.json();})
    .then(function(){
        console.log(numTurno);
    })
}

