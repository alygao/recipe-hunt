const { writeFile } = require('fs');
const colors = require('colors');

const envConfigFile = `export const environment = {
   apiURL: 'http://${process.env.API_URL_HOST}:${process.env.API_URL_PORT}',
   production: true,
};
`;

const targetPath = `./src/environments/environment.ts`;
console.log(colors.magenta(`The file ${targetPath} will be written with the following content: \n`));
console.log(colors.grey(envConfigFile));

writeFile(targetPath, envConfigFile, (err) => {
    if (err) {
        throw console.error(err);
    } else {
        console.log(colors.magenta(`Angular environment was generated correctly at ${targetPath} \n`));
    }
});