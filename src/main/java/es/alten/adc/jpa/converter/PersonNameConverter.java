package es.alten.adc.jpa.converter;

import javax.persistence.AttributeConverter;

import es.alten.adc.jpa.entity.PersonName;

/**
 * Converts a {@link PersonName} instance to a String column and vice-versa
 *
 */
public class PersonNameConverter implements AttributeConverter<PersonName, String>{
	
	private static final String SEPARATOR = ", ";

	@Override
	public String convertToDatabaseColumn(PersonName personName) {
		String str = null;
		if(personName != null) {
			final StringBuilder sb = new StringBuilder();
			if(personName.getSurname() != null && !personName.getSurname().isEmpty()) {
				sb.append(personName.getSurname())
					.append(SEPARATOR);
			}
			if(personName.getName() != null && !personName.getName().isEmpty()) {
				sb.append(personName.getName());
			}
			
			str = sb.toString();
		}
		return str;
	}

	@Override
	public PersonName convertToEntityAttribute(String dbPersonName) {
		PersonName pn = null;
		if(dbPersonName != null && !dbPersonName.isEmpty()) {
			final String[] pieces = dbPersonName.split(SEPARATOR);
			if(pieces != null && pieces.length > 0) {
				pn = new PersonName();
				final String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
				if(dbPersonName.contains(SEPARATOR)) {
					pn.setSurname(firstPiece);
					
					if(pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) {
						pn.setName(pieces[1]);
					}
				} else {
					pn.setName(firstPiece);
				}
			}
		}
		return pn;
	}

}
