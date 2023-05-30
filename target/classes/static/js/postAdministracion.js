window.onload = function(){
    let btnGuardar = document.querySelector("#guardar");
    let data;
    let url;
    btnGuardar.addEventListener("click",function(e){
        e.preventDefault();
        let form = document.querySelector(".sectionIngreso form");
        let organizador = [];
        form[0].checked ? organizador.push(form[0].value) : organizador.push(form[1].value);
        for(let i = 2; i< form.length-1; i++){
            organizador.push(form[i].value);
        }
        if(form[2].id == "matricula"){
            data = {
                ROLE: organizador[0],
                user: organizador[2],
                password: organizador[3],
                nombre: organizador[4],
                apellido: organizador[5],
                matricula: organizador[1]
            }
            url = '/odontologo/guardar';
        }else{
            data = {
                ROLE: organizador[0],
                user: organizador[2],
                password: organizador[3],
                nombre: organizador[4],
                apellido: organizador[5],
                DNI: organizador[1],
                domicilio: organizador[6],
                fechaAlta: organizador[7]
            }
            url = '/paciente/guardar';
        }
        let settings = {
            method:'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        console.log(url);
        console.log(settings);
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

