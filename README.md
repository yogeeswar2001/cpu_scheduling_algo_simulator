# cpu_scheduling_algo_simulator
### Working
This uses ajax for sending the input data to server, and the server processes the input data and passes it to executable as command line arguments, output of c++ program is processed by adding HTML tags to it and this is served back to the user. DOM elements are used to render the content
### Compiling c++ code
- Use makefile to compile c++ file
  ```
  make recomplie
  ```
- Note: c++ code is already compiled, object files are kept in obj directory and executable is placed in target directory
### How to run the web-app
##### Follow the below steps for apache server
- Create the directory for your_domain and move to the directory
  ```
  sudo mkdir /var/www/your_domain
  cd /var/www/your_domain
  ```
- Clone the repo or download the files inside the current directory,To Clone the repo
    ```
    git clone "https://github.com/yogeeswar2001/cpu_scheduling_algo_simulator.git"
    ```
- Assign ownership of the directory with the $USER environment variable
  ```
  sudo chown -R $USER:$USER /var/www/your_domain
  ```
- open a new configuration file in Apache sites-available directory using preferred command-line editor
  ```
  sudo vim /etc/apache2/sites-available/your_domain.conf
  ```
- Paste in the following configuration
  ```
  <VirtualHost *:80>
    ServerName your_domain
    ServerAlias www.your_domain
    ServerAdmin webmaster@localhost
    DocumentRoot /var/www/your_domain
    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
  </VirtualHost>
  ```
- Run the following commands to enable the web app
  ```
  sudo a2ensite your_domain
  sudo a2dissite 000-default
  ```
  The below command should give "syntax OK" which ensures configuration file doesn’t contain syntax error, if so reload apache
  ```
  sudo apache2ctl configtest
  ```
  ```
  sudo systemctl reload apache2
  ```
- Open the browser and type localhost in the url
