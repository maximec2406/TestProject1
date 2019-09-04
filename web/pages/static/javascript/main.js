function deleteGenre(str){
   postGenre('http://localhost:8080/deleteGenre', str);
}

function editGenre(str) {
    var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
    var header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
    var xhr = new XMLHttpRequest();

    var body = 'name=' + encodeURIComponent(str);

    xhr.open("get", 'http://localhost:8080/editGenre?name=' + encodeURIComponent(str), true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.setRequestHeader(header,token);
    xhr.onreadystatechange = function() {
        if (xhr.status === 200 && xhr.readyState === 4 ) {
            alert("Страница загружена");
            // document.location. href = xhr.responseURL;
        }
    }
    xhr.send();
}

function postGenre(url, str) {
    var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
    var header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
    var xhr = new XMLHttpRequest();

    var body = 'name=' + encodeURIComponent(str);

    xhr.open("post", url, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.setRequestHeader(header,token);
    xhr.onreadystatechange = function() {
        if (xhr.status === 200 && xhr.readyState === 4 ) {
            alert("Изменения сохранены")
            document.location.href = xhr.responseURL;
        }
    }
    xhr.send(body);
}