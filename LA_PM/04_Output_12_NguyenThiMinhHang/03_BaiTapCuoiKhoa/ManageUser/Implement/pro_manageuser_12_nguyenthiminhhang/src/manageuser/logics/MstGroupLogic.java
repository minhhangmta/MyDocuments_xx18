/**
* Copyright(C) 2017 Luvina
* MstGroupLogic.java, Oct 26, 2017 minhhang
*/
package manageuser.logics;

import java.util.List;

import manageuser.entities.MstGroup;

/**
 * Interface Xử lý logic cho list group
 * 
 * @author minhhang
 */
public interface MstGroupLogic {
	/**
	 * Hàm lấy danh sách group
	 * 
	 * @return List<MstGroup> danh sách group
	 */
	public List<MstGroup> getAllGroups();
}
