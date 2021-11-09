function isSameOrigin (url1, url2) {
  return url1.protocol === url2.protocol &&
    url1.hostname === url2.hostname &&
    url1.port === url2.port
}

// Demo 1
const iframe = document.createElement('iframe')
iframe.src = 'https://web.stanford.edu/class/cs106a/'
document.body.appendChild(iframe)

// Same origin, so everything is allowed
iframe.contentDocument.body.style.backgroundColor = 'red'

// Even this!
let i = 0
const imgs = iframe.contentDocument.body.querySelectorAll('img')
setInterval(() => {
  imgs.forEach(img => img.style.transform = `rotate(${i}deg)`)
i += 1 }, 50)

// Demo 2
const iframe = document.createElement('iframe')
iframe.src = 'https://crypto.stanford.edu'

// Is this allowed?
document.body.append(iframe) // Allowed!

// Is this allowed?
iframe.contentDocument.body.style.backgroundColor = 'red' // Not allowed!

// Navigate to Dan's homepage, then...

// Is this allowed?
iframe.src = 'https://example.com' // Allowed! Surprised?