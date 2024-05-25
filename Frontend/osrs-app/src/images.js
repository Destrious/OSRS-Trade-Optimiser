// src/item_images/images.js
// Function to import image
export async function importImage(imageName) {
    try {
        let trimmedImageName = imageName;
        if (imageName.endsWith(' 5.png')) {
            trimmedImageName = imageName.replace(' 5', '');
        }
        const image = await import(`./item_images/${trimmedImageName}`);
        return image.default;
    } catch (error) {
        console.error(`Failed to load image: ${imageName}`);
        return null; // or return a default image
    }
}