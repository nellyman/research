
package uk.co.honda.hi.data.ssdif.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import uk.co.honda.hi.value.ssdif.SSDifBean;

/**
 * @author uinxh
 * DAO to insert XML documents into database.
 * 
 */
public class SSDIFDAO extends JdbcDaoSupport{

	private static final String DEST_SQL = "select NDESTN AS ID from WBCOSDIF.DSTLUT  where UPPER(XNAME) = UPPER(CAST(? AS CHAR(40)))";
	private static final String CAMP_SQL = "SELECT NCAMPN AS ID FROM WBCOSDIF.CAMLUT where UPPER(XNAME) = UPPER(CAST(? AS CHAR(40)))";
	
	private static final String SEQUENCE_SQL = "Select (max(NSEQ)+1) as ID from WBCOSDIF.SSDifT";
		
	private static final String ID="ID";

	private static final String INSERT_SQL= "INSERT INTO WBCOSDIF.SSDifT (XMLDATA, DSTLUT01, CAMLUT01, TCREATED)  "+
										" VALUES (?, ?, ?, CURRENT TIMESTAMP)";

	
	/**
	 * Used for sequencing the documents.
	 * @return The next ID free in the SSDIF table.
	 * @throws Exception
	 */
	public int getNextSequenceID() throws Exception{
		
		List rows = getJdbcTemplate().queryForList(
						SEQUENCE_SQL, 
						new Object[] { },
						new int[] {});
	   				
		Map results = (Map) rows.get(0);
		return ((Integer)results.get(ID)).intValue();
		
	}
	
	/**
	 * From the destination lookup table will return a destination ID for 
	 * a destination Name 
	 * @param destName String
	 * @return int Destination Id corresponding to the ID.
	 * @throws Exception
	 */
	public int getDestinationID(String destName) throws Exception{
		
		List rows = getJdbcTemplate().queryForList(
						DEST_SQL, 
						new Object[] { 
							destName},
						new int[] {
							Types.VARCHAR});
	   				
		Map results = (Map) rows.get(0);
		return ((Integer)results.get(ID)).intValue();
	}
	
	/**
	 * Gets the campaign Id from a campaign name.
	 * @param campaignName String
	 * @return int campaignID
	 * @throws Exception
	 */
	public int getCampaignID(String campaignName) throws Exception{
		
		List rows = getJdbcTemplate().queryForList(
				CAMP_SQL, 
				new Object[] { 
					campaignName},
				new int[] {
					Types.VARCHAR});
	   				
		Map results = (Map) rows.get(0);
		return ((Integer)results.get(ID)).intValue();
	}
	
		
	
	/**
	 * Inserts a Bean into the database
	 * @param bean SSDifBean containing destinationId, XML data and Campaign descriptor.
	 * @return boolean insert successful.
	 * @throws Exception
	 */
	public boolean insert(SSDifBean bean) throws Exception{
		int rowsUpdated = getJdbcTemplate().update(
			INSERT_SQL,
			new Object[] {	
				bean.getXmlData(),
				new Integer(bean.getDestinationId()),
				new Integer(bean.getCampaignDescriptor())},
			new int[]{
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER});
				
		if (rowsUpdated>0){
			return true;
		}
		return false;
	}
	
	
	
}
