SELECT count(au.id) AS count
	FROM AUTH_USER au
	JOIN AUTH_USER_ROLE aur ON aur.user_id = au.id
	JOIN AUTH_ROLE ar ON aur.role_id = ar.id;