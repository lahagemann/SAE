# SAE

Software Engineering Course: Final Project.

<b>about the client:</b><br>
A mock-company asked for an energy-consumption solution. The system developed should be able to turn all the company's resources on and off remotely (via web). 

Each employee has access to the resources in the room they work in. Admins/Managers have access to all rooms. <br><br>
All rooms have an energy consumption goal. If by the end of the month that goal is met, all room employees receive a bonus.<br><br>
An employee can register a custom action, which can turn on and off multiple resources in his room.

<b>system architecture:</b><br>
This system was developed using only Java, JSP and a MySql Database system. As it would be quite impossible for a course project, the actual turning on and off of devices was abstracted using a multitier (4-tier) architecture. All tiers are completely isolated using façades and DAO patterns.
