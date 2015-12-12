<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title> Forgotten password</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
    </head>
    <body>
        <form method="post" action="Forgetting">
            <fieldset>
                <legend>Regeneration password</legend>
               

                <label for="email">Please enter your email address<span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${erreurs['email']}</span>
                <br />

                <input type="submit" value="Send" class="sansLabel" />
                <br />
                
                <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
                
                
            </fieldset>
        </form>
    </body>
</html>