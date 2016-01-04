<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Add announcement</title>
<link href="<c:url value="/CSS/bootstrap.min.css"/>" rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/CSS/style.css"/>" />
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="./resource/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="./resource/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="./resource/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="./resource/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="./resource/ico/favicon.png">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
</head>
<body>
	<c:import url="/inc/headerUser.jsp" />
	<c:import url="/inc/menuUser.jsp" />
	<div id="corpsDa">
		<h1 id="Inscription-title">Contact</h1>

		<br>
		<div class="contactImage">
			<a id="mail"><img title="Post by email"
				src="<c:url value="/inc/mail.png"/>" /></a>

			<c:if test="${current_announce.user.phone != null}">
				<a id="phone"> <img title="Post by phone"
					src="<c:url value="/inc/tel.jpg"/>" /></a>
			</c:if>

			<a> <img title="Post by chat, The seller is online"
				src="<c:url value="/inc/chat.png"/>" /></a>

		</div>

		<form class="contact" role="form" method="post" action="contact">
			<fieldset id="Field">
				<legend>Contact by post</legend>
				<label> Announce : </label><input name="Name"
					value="${current_announce.name}" disabled><br> <br>
				<input type="hidden" name="annonces" value="${current_announce.id}">
				<label> Description : </label>
				<textarea style="width: 60%;" type="text" name="Message" requiered> </textarea>
				<br>
				<button type="submit" value="send message" class="btn register">
					Send Message</button>
			</fieldset>
		</form>


		<h3 id="mail">Email : ${current_announce.user.email}</h3>
		<h3 id="phone">Phone : ${current_announce.user.phone}</h3>
	</div>

	<c:if test="${!empty connexion}">
		<div id="corpsDa">
			<div class="container chat-signin">
				<form class="form-signin">
					<button class="btn btn-large btn-primary" type="submit"
						id="enterRoom">chat</button>
				</form>
			</div>
			<!-- /container -->

			<div class="container chat-wrapper">
				<form id="do-chat">
					<h2 class="alert alert-success"></h2>
					<table id="response" class="table table-bordered"></table>
					<fieldset>
						<legend>Enter your message..</legend>
						<div class="controls">
							<input type="text" class="input-block-level"
								placeholder="Your message..." id="message" style="height: 60px" />
							<input type="submit" class="btn btn-large btn-block btn-primary"
								value="Send message" />
							<button class="btn btn-large btn-block" type="button"
								id="leave-room">Leave room</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</c:if>
	<script>
		jQuery(document).ready(function() {

			$("h3#mail").hide();
			$("h3#phone").hide();
			jQuery('a#phone').click(function() {
				$("h3#phone").show();
			});

			jQuery('a#mail').click(function() {
				$("h3#mail").show();
			});
		});
	</script>

	<script>
		var wsocket;
		var serviceLocation = "ws://0.0.0.0:8080/alda_project/chat/";
		var $nickName;
		var $message;
		var $chatWindow;
		var room = '';

		function onMessageReceived(evt) {
			var msg = JSON.parse(evt.data); // native API
			var $messageLine = $('<tr><td class="received">' + msg.received
					+ '</td><td class="user label label-info">' + msg.sender
					+ '</td><td class="message badge">' + msg.message
					+ '</td></tr>');
			$chatWindow.append($messageLine);
		}
		function sendMessage() {
			var msg = '{"message":"' + $message.val() + '", "sender":"'
					+ $nickName.val() + '", "received":""}';
			wsocket.send(msg);
			$message.val('').focus();
		}

		function connectToChatserver() {
			room = $
			{
				current_announce.user.email
			}
			;
			wsocket = new WebSocket(serviceLocation + room);
			wsocket.onmessage = onMessageReceived;
		}

		function leaveRoom() {
			wsocket.close();
			$chatWindow.empty();
			$('.chat-wrapper').hide();
			$('.chat-signin').show();
			$nickName.focus();
		}

		$(document).ready(
				function() {
					$nickName = $
					{
						sessionScope.user.name
					}
					;
					$message = $('#message');
					$chatWindow = $('#response');
					$('.chat-wrapper').hide();
					$nickName.focus();

					$('#enterRoom').click(
							function(evt) {
								evt.preventDefault();
								connectToChatserver();
								$('.chat-wrapper h2').text(
										'Chat # ' + $nickName.val() + "@"
												+ room);
								$('.chat-signin').hide();
								$('.chat-wrapper').show();
								$message.focus();
							});
					$('#do-chat').submit(function(evt) {
						evt.preventDefault();
						sendMessage()
					});

					$('#leave-room').click(function() {
						leaveRoom();
					});
				});
	</script>
</body>
</html>