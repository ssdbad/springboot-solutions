create or replace PROCEDURE TestUser_Cleanup(I_User_Id IN INTEGER,O_Status OUT INTEGER)
IS

l_code VARCHAR2(1000);
l_msg  VARCHAR2(4000);

BEGIN

DELETE FROM user_protocol_access WHERE user_id = I_User_Id;
DELETE FROM user_process_access WHERE user_id = I_User_Id;
DELETE FROM user_role WHERE user_id = I_User_Id;

O_STATUS := 1;

COMMIT;

EXCEPTION 
   WHEN OTHERS THEN 
   BEGIN 
   ROLLBACK;
   O_STATUS := 0;
   l_code := SQLCODE;
   l_msg  := 'An error was encountered - TestUser_Cleanup'||SQLCODE||' -ERROR- '||SQLERRM;
   END;
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END TestUser_Cleanup;