<form style="text-align: right">
	<select name="language" onchange='this.form.submit()'>
		<c:choose>
			<c:when test="${sessionScope['language']}">
				<option value='ru' selected>Русский
				<option value='en'>English
			</c:when>
			<c:otherwise>
				<option value='en' selected>English
				<option value='ru'>Русский
			</c:otherwise>
		</c:choose>
	</select>
</form>