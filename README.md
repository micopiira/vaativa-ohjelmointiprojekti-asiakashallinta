# Customer management CRUD

## Settings

To modify settings like database connection properties you can edit the `src/main/resources/application.properties` file.

Tables should be automatically created by Hibernate as long as the connection settings are correct.

All settings can also be overridden using environment variables.

## Building

To build a deployable WAR file, running `mvn package` should be enough.
 
### Frontend

If you need to modify any JavaScript or CSS files you will need to rebuild the frontend assets.

To bundle (and minify) all JavaScript and CSS into `src/main/webapp/dist` with [Webpack](https://webpack.js.org/) you need to run:

With npm:

    npm install
    npm run build
    
With yarn:
    
    yarn
    yarn build
    
To continuously build the frontend assets as you edit them, you can use the `watch` npm script.