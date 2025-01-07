document.addEventListener("DOMContentLoaded", () => {
    let iteration = 0;
    typeWriter(iteration); 
    setInterval(() => {
        iteration++;
        typeWriter(iteration);
    }, 7000);
});

function typeWriter(iteration) {
    const text = "Fast Delivery";
    const speed = 200;
    let i = 0;
    const demoElement = document.getElementById("demo");
    demoElement.innerHTML = "";

    function type() {
        if (i < text.length) {
            let colorClass = "";
            if ([0, 1, 9, 10, 11, 12].includes(i)) {
                colorClass = iteration % 2 === 0 ? "red" : "yellow";
                demoElement.innerHTML += `<span class="${colorClass}">${text.charAt(i)}</span>`;
            } else {
                demoElement.innerHTML += text.charAt(i);
            }
            i++;
            setTimeout(type, speed);
        }
    }

    type();
}


