

Major dones:
1) In order to allow file upload on different endpoints, find the absolutepath:
	String cwd = Path.of("").toAbsolutePath().toString();
then append it with the receipt images folder
	cwd  += "\\receiptImages\\"
then save it in environment variables

Now, the code will retrieve the path and affix the ticket_id to it and the image will be saved with the same id of the ticket

Priorities:
1) Unit testing of Services
2) Front end is taken care by teammate.
3) finance manager should be able to view the receipt of a ticket, 
