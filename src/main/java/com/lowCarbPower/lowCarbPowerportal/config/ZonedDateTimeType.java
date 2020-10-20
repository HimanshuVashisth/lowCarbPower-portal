package com.lowCarbPower.lowCarbPowerportal.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.TimestampType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

//hibernate envers 5.1 does not support converters: https://hibernate.atlassian.net/browse/HHH-9042
public class ZonedDateTimeType implements UserType {

//    private ZonedDateTimeConverter zonedDateTimeConverter = new ZonedDateTimeConverter();

    @Override
    public int[] sqlTypes() {
        return new int[]{TimestampType.INSTANCE.sqlType()};
    }

    @Override
    public Class returnedClass() {
        return ZonedDateTime.class;
    }

    @Override
    public boolean equals(Object obj1, Object obj2) throws HibernateException {
        return (obj1 == null && obj2 == null) || // prevent hibernate to set dirty flag for null values which causes updates even for loading entities
                ((obj1 != null && obj2 != null) && obj1.equals(obj2));
    }

    @Override
    public int hashCode(Object obj) throws HibernateException {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

//    @Override
//    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor sessionImplementor, Object owner) throws HibernateException, SQLException {
//        java.util.Date value = (java.util.Date) TimestampType.INSTANCE.get(resultSet, names[0], sessionImplementor);
//        return zonedDateTimeConverter.convertToEntityAttribute(value);
//    }
//
//    @Override
//    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
//        if (value == null) {
//            TimestampType.INSTANCE.set(preparedStatement, null, i, sessionImplementor);
//        } else {
//            java.util.Date convertedValue = zonedDateTimeConverter.convertToDatabaseColumn((ZonedDateTime) value);
//            TimestampType.INSTANCE.set(preparedStatement, convertedValue, i, sessionImplementor);
//        }
//    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
