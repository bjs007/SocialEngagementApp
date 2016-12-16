# Engage

We have created a java based web application which runs in a tomcat server. The 
application uses MySql database.

## Summary of the project
This application will provide the old people a platform which will help them 
coordinate with society and get help for even the daily activities which they 
find difficult. For example, some seniors are no longer able to drive a car. 
Ideally, other people living in the same community can share them a ride if they
all agree on the schedule they set before. 
Our application will offer two different roles. For the seniors, they can log in
this application, creating, viewing and discussing social events. For staff 
members, they not only have the same privileges as the seniors’, but more 
privileges managing those activities. 
The application is designed for senior citizens, keeping in mind the factor of 
ease of use. Notifications, for example, will let the seniors know some kinds of
social activities is coming.
We shall talk a bit more about each modules in more details below.

--------------------------------------
### End user's guide:
In our project there are 5 main modules. They are:
1. Events
2. Broadcast
3. Discussion
4. Dashboard
5. Sms Notification

#### Events
Events is a multipurpose module that can be used in several ways by the seniors. 
We have tried to keep this module a generic one. Usually an events needs:
- Description/purpose of the event
- Resources
- To know whether all resources are satisfied
- Date & Time for an event
- Place of event
- When this notification is to be displayed 
- Comments section so that other seniors can communicate and share their ideas
- Archiving functionality to set it as old

We are allowing the users to enter this details from the Create Event [ **Create a
new Event** ] 
page. After this event is created other users can view this event through **See 
all active events**. Other user can go through these events and comment on these
events. They can mention in the comment whether they are willing to join the event or not
and whether they can bring any required resources.
Users can also go to the **See all Archived Events** section to see all the past events.
They can come to know which user is interested in these type of events.
After a event has been held, the event's creator or the Admin can set it to Archived.

**Access Control in Event module**

Care has been taken to see that only authorised user are able to make the necessary changes.
Only the Event's creator are able to modify the important information for an event. He will 
be able to mark the Event as Archived and set whether the required resources are satisfied.
He can delete the Event is he wishes.
Other users can only post a comment on the page. He will see all other fields disabled.
The Admin users(Community Representative) have access to modify or delete the events.

#### Broadcast

#### Discussion

#### Dashboard


#### Sms Notification
This is a simple Java project which uses a Third party API to remind the event's creator about
their events.

--------------------------------------

### Technical Guide:

This application is a SpringMvc based web-application. The project integrates 
Maven for dependency management and Apache Tiles for keeping the UI
consistent accross all the pages.
We use MySql as a backend database and use Jdbc to connect to database.
The database configuration documement has been checked in Git. 
Steps to deploy the application has also been checked in.

**To import this project**

As this is a Maven project,we can simple clone the project into our local 
environment and import the project in Eclipse as an existing Maven project.

**How to use the application**

*End user's guide* module at the start of this page has been provided for this purpose.

**Team members & contacts**

Dipanjan Karmakar: dipanjan@iastate.edu  
Prateek Gupta:     prateekg@iastate.edu   
Nikita Tiwari:     prateekg@iastate.edu   
Lei Liu:		   lliu@iastate.edu  

**External resource links**

In the Sms notification module we use a Third Party API for sending SMS.
For more details please refer the official page [TextBelt website](http://textbelt.com)
The code is checked in the “smsnotification” branch in git.





