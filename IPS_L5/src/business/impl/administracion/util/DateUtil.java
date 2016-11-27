package business.impl.administracion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import business.exception.BusinessException;

public class DateUtil {

	public static SimpleDateFormat sdf_dd_MM_yyyy = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat("yyyy/MM/dd");

	public static Date truncarHastaDia(Date fecha) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(fecha);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date clonarFecha(Date fecha) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(fecha);

		return cal.getTime();
	}

	public static Date sumarDia(Date fecha) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(fecha);
		cal.add(Calendar.DATE, 1);

		return cal.getTime();
	}

	public static boolean mismoDia(Date fecha1, Date fecha2) {
		return sdf_dd_MM_yyyy.format(fecha1).equals(sdf_dd_MM_yyyy.format(fecha2));
	}

	/**
	 * Cambia el formato de una fecha que se le pasa como parámetro.
	 * 
	 * @param fecha
	 *            String con formato yyyy/MM/dd
	 * 
	 * @return String con formato String dd/MM/yyyy
	 * 
	 * @throws BusinessException
	 *             no se ha podido transformar la fecha
	 * 
	 */
	public static String cambiarFormatoFecha(String fecha) throws BusinessException {
		String fechaSalida = "";

		try {
			Date date = sdf_yyyy_MM_dd.parse(fecha);
			fechaSalida = sdf_dd_MM_yyyy.format(date);
		}

		catch (ParseException e) {
			throw new BusinessException("La fecha procesada no es válida", e);
		}

		return fechaSalida;
	}

	/**
	 * Crea una fecha a partir del año, el mes y el día.
	 * 
	 * @param year
	 *            año de la fecha a crear
	 * @param month
	 *            mes de la fecha a crear
	 * @param day
	 *            dia de la fecha a crear
	 * 
	 * @return fecha con ese dia, mes, año
	 * 
	 */
	public static Date getDate(int year, int month, int day) {
		try {
			return sdf_dd_MM_yyyy.parse(day + "/" + month + "/" + year);
		}

		catch (ParseException e) {
			return null;
		}
	}

}