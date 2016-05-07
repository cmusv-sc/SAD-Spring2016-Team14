Folder Hierarchy:
Code is in:
ApacheCMDA-Frontend Folder
ApacheCMDA-Backend Folder

Documentation in:
Documents Folder


The purpose of this project is to modify original code applying to 9 different design pattern. In the website a user can interact with other users in multiple ways. 

To run the website, download all files from github:
(https://github.com/varunv123/SAD-Spring2016-Team14).

Then make sure mysql is already installed. To load website database, create a new database in mysql named testdb. Then Cd into /ApacheCMDA-Backend/DBDump, import dumped database into testdb using the following command
Mysql –u root –p testdb<dumpfile.sql

Then cd into /ApacheCMDA-Backend, in the command line type:
./activator “run 9034”

Cd into /ApacheCMDA-Frontend, in the command line type:
./activator run

Now the web site is running on localhost:9000. To visit simply open any browser and type localhost:9000.