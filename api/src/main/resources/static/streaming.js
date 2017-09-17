const intervals = {};
document.querySelectorAll('.container').forEach((it, idx) => {
  intervals[idx + 1] = {
    el: it,
    inte: setInterval(() => {
      it.innerHTML += "-";
    }, 500)
  };
});
oboe('/stream/data')
  .node('!', (it) => {
    let container = intervals[it.id];
    clearInterval(container.inte);
    container.el.innerHTML += `|${it.name}`;
    console.log('Got value');
    console.log(it);
  })
  .done(() => {
    console.log('All done for this object');
  });