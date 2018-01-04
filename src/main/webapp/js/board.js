$(updateSortable);

function updateSortable() {
    $("#todo, #doing, #done").sortable({
        connectWith: ".kb-stage-content",
        placeholder: 'kb-card-placeholder',
        stop: function (event, ui) {
            var li = ui.item[0];

            onDrop({id: li.dataset.id, stage: li.parentElement.dataset.stage, index: ui.item.index()});
        }
    }).disableSelection();
}

function onDeteleteCard() {
    $('#cardModal').modal("hide");
    updateSortable();
}

function openCardEditor(data) {
    if (data.status === "success") {
        $('#cardModal').modal();
    }
}