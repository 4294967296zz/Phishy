var token = $("input[name='_csrf']").val();
var header = "X-CSRF-TOKEN";

// url get parameter 가져오기
function geturlparam (name) {
  var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
  if (results==null){
    return null;
  } else{
    return results[1] || 0;
  }
}

// 파일 타입 체크
function checkFileType(filePath) {
    var fileFormat = filePath.split(".");
    if (fileFormat.indexOf("xlsx") > -1) {
        return true;
    } else {
        return false;
    }
}