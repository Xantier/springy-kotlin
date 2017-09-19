oboe('/stream/resty')
  .node("!", (response) => {
    console.log(response);
    const img = document.createElement("img");
    img.src = response.photo_link;
    img.alt = response.member.name;
    img.title = response.member.name + ' - ' + response.caption;
    document.getElementById("photos").appendChild(img);
  });