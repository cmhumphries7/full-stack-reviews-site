// List tags
let tagItems = [];
let reviewId = document.querySelector('#reviewId').textContent;
getItems();

function getItems() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(xhr.response);
            const tagItems = JSON.parse(xhr.response);
            renderTags(tagItems);
        }
    };
    xhr.open('GET', '/review/' + reviewId + '/tags', true);
    xhr.send();
}


// Add New Tag

function addTag(event) {
    event.preventDefault();


    const tagName = event.target.querySelector('[name=tagName]').value;
    const reviewId = event.target.querySelector('[name=reviewId]').value;

    const addNewTag = JSON.stringify({
        reviewId: +reviewId,
        tagName,
    });

    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            tagItems = JSON.parse(xhr.response);
            renderTags(tagItems)
        }
    };

    xhr.open('PUT', './review/add-new-tag');
    xhr.setRequestHeader(
        'Content-Type',
        'application/json;charset=UTF-8'
    );
    xhr.send(addNewTag);
}

// Remove Tag
function removeTag(tagName) {
    event.preventDefault();

    console.log(event);

    console.log({
        reviewId: +reviewId,
        tagName,
    });
    const removeTag = JSON.stringify({
        reviewId: +reviewId,
        tagName,
    });

    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            tagItems = JSON.parse(xhr.response);
            renderTags(tagItems);
        }
    }
    xhr.open('DELETE', '/review/removetag')
    xhr.setRequestHeader(
        'Content-Type',
        'application/json;charset=UTF-8'
    );
    xhr.send(removeTag)
}

// Show Tags
function renderTags(tagItems) {
    const showTagsDiv = document.querySelector('#showTags');
    showTagsDiv.innerHTML = '';


    console.log({ tagItems });
    tagItems.forEach(tag => {

        const tagHref = '/tag?id=' + tag.id;


        const tagName = document.createTextNode(tag.name);
        const tagDiv = document.createElement('div')
        const tagLink = document.createElement('a');
        tagLink.setAttribute('href', tagHref);
        tagLink.appendChild(tagName);
        tagDiv.appendChild(tagLink);


        const tagRemoveButton = document.createElement('button');
        tagRemoveButton.innerHTML = '&times;';
        tagRemoveButton.title = 'Remove Tag';
        tagRemoveButton.id = tag.name;
        tagRemoveButton.addEventListener('click', function () {
            removeTag(tag.name);
        });
        tagDiv.appendChild(tagRemoveButton);


        showTagsDiv.appendChild(tagDiv);
    })
}