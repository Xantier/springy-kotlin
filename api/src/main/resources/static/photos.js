oboe('/stream/resty')
  .node("!", (response) => {
    console.log(response);
    const img = document.createElement("img");
    img.src = response.photo_link;
    img.alt = response.member.name;
    document.getElementById("photos").appendChild(img);
  });