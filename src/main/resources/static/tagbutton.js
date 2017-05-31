
function main(){
//
//$("form").submit(function(e) {
//  e.preventDefault();
//  alert($(this).serialize());
//});


$("#addTagButton").click(function(e) {
  e.preventDefault();
  console.log("in click handler")
  console.log("tagInput value: "+$("#tagInput"))
  if ($('#tagInput').val() !== '') {
  	console.log("Tag added")
    $("#tagList").append($("<option>", {
      value: $("#tagInput").val(),
      selected: "selected",
      text: $("#tagInput").val()
    }));
    $("#tagInput").val("");
  }
});
$('[data-toggle="popover"]').popover();
}
$(document).ready(main);
