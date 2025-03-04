function validar() {
    let tipoDocumento = $("input[name='tipoDocumento']:checked").val();
    
    $.ajax({
        method: "POST",
        url: "/validador-documentos",
        data:{
            "documento": $("#documento").val(),
            "tipoDocumento": tipoDocumento
        }
    }).done(function (data) {
        if(data){
            mostrarModal("Validação","O " + tipoDocumento.toUpperCase() + " é válido!", "OK")
        }
        else{
            mostrarModal("Validação","O " + tipoDocumento.toUpperCase() + " é inválido!", "OK")
        }
    });
}

function mostrarModal(title, bodyText, confirmButtonText) {
    // Verificar se o modal já existe para evitar duplicação
    if (document.getElementById('confirmModal')) {
        document.getElementById('confirmModal').remove();
    }

    // Criar o elemento modal
    let modalHtml = `
        <div id="confirmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" style="bottom:10%" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title title-secao fw-bold" id="confirmModalLabel">${title}</h5>
                        <a type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </a>
                    </div>
                    <div class="modal-body">
                        ${bodyText}
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-custom" data-dismiss="modal">${confirmButtonText}</button>
                    </div>
                </div>
            </div>
        </div>
    `;

    // Adicionar o modal ao body do documento
    document.body.insertAdjacentHTML('beforeend', modalHtml);

    // Abrir o modal usando jQuery (ou manualmente)
    $('#confirmModal').modal('show');
}