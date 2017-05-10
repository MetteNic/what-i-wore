
function main(){
$("form").submit(function(e) {
  e.preventDefault();
  alert($(this).serialize());
});

$("#addFooButton").click(function(e) {
  e.preventDefault();
  console.log("in click handler")
  console.log("fooInput value: "+$("#fooInput"))
  if ($('#fooInput').val() !== '') {
  	console.log("Foo has value")
    $("#fooList").append($("<option>", {
      value: $("#fooInput").val(),
      selected: "selected",
      text: $("#fooInput").val()
    }));
    $("#fooInput").val("");
  }
});
}
$(document).ready(main);
