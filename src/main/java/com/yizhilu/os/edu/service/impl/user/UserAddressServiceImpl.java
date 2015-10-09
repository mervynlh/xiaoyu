package com.yizhilu.os.edu.service.impl.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.course.TeacherDao;
import com.yizhilu.os.edu.dao.user.UserAddressDao;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.user.UserAddress;
import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.service.user.UserAddressService;
import com.yizhilu.os.edu.service.user.UserAreaService;
/**
 * UserAddress管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService{

 	@Autowired
    private UserAddressDao userAddressDao;
 	@Autowired
 	private TeacherDao teacherDao;
    
    /**
     * 添加UserAddress
     * @param userAddress 要添加的UserAddress
     * @return id
     */
    public java.lang.Long addUserAddress(UserAddress userAddress){
    	//判断是否存在默认地址  不存在本地址将是默认地址
    	int haveFirst=userAddressDao.queryHaveFirst(userAddress.getUserId());
    	if(haveFirst==0){
    		userAddress.setIsFirst(1);
    	}
    	userAddress.setCreateTime(new Date());
    	Long id=userAddressDao.addUserAddress(userAddress);
    	//更新教师表的cityid townid字段
    	setCityTownId(userAddress.getUserId());
    	return id;
    }
    
    /**
     * 更新教师表的cityid townid字段
     * @param userAddress
     */
    private void setCityTownId(Long userId){
    	Teacher teacher= teacherDao.getTeacherByUserId(userId);
    	//查询不到教师将不更新教师操作
    	if(ObjectUtils.isNotNull(teacher)){
	    	//获取用户的常用地址
	        UserAddress queryAddress=new UserAddress();
	        queryAddress.setUserId(userId);
	        List<UserAddress> addressList= this.getUserAddressList(queryAddress);
	        if(ObjectUtils.isNotNull(addressList)&&addressList.size()>0){
	    		teacher.setCityId(addressList.get(0).getCityId());
	        	teacher.setDistrictId(addressList.get(0).getTownId());
	        	//用户未设定常用地址的情况下获取第一个地址
	        	for(UserAddress address:addressList){
	            	if(address.getIsFirst()==1){
	            		teacher.setCityId(address.getCityId());
	                	teacher.setDistrictId(address.getTownId());
	            		break;
	            	}
		        }
	        	teacherDao.updateCityTown(teacher);
	    	}
        }
    }

    /**
     * 根据id删除一个UserAddress
     * @param id 要删除的id
     */
    public void deleteUserAddressById(Long userId,Long id){
    	 userAddressDao.deleteUserAddressById(id);
    	//更新教师表的cityid townid字段
     	setCityTownId(userId);
    }
    /**
     * 根据id设置常用地址
     */
    public void updateUserAddressById(Long id,Long userId){
    	//更新该用户下全部的地址为不常用地址
    	UserAddress userAddress = new UserAddress();
    	userAddress.setIsFirst(0);
    	userAddress.setUserId(userId);
    	userAddressDao.updateUserAddressisFirst(userAddress);
    	//更新用户所选id为常用地址
    	userAddress = new UserAddress();
    	userAddress.setIsFirst(1);
     	userAddress.setId(id);
     	userAddressDao.updateUserAddressisFirst(userAddress);
     	//更新教师表的cityid townid字段
    	setCityTownId(userId);
    }
    /**
     * 修改UserAddress
     * @param userAddress 要修改的UserAddress
     */
    public void updateUserAddress(UserAddress userAddress){
     	userAddressDao.updateUserAddress(userAddress);
     	//更新教师表的cityid townid字段
    	setCityTownId(userAddress.getUserId());
    }
    
    /**
     * 根据id获取单个UserAddress对象
     * @param id 要查询的id
     * @return UserAddress
     */
    public UserAddress getUserAddressById(Long id){
    	UserAddress address=userAddressDao.getUserAddressById( id);
    	Map<Long,UserArea> map = userAreaService.getMapUserAreaList(new UserArea());
    	UserArea areaC = map.get(address.getCityId());
		UserArea areaP = map.get(address.getProvinceId());
		UserArea areaT = map.get(address.getTownId());
		if(ObjectUtils.isNotNull(areaC)){
			address.setCityStr(areaC.getAreaName());
		}
		if(ObjectUtils.isNotNull(areaP)){
			address.setProvinceStr(areaP.getAreaName());
		}
		if(ObjectUtils.isNotNull(areaT)){
			address.setTownStr(areaT.getAreaName());
		}
        return address;
        
    }
    @Autowired
    private UserAreaService userAreaService;
    /**
     * 根据条件获取UserAddress列表
     * @param userAddress 查询条件
     * @return List<UserAddress>
     */
    public List<UserAddress> getUserAddressList(UserAddress userAddress){
    	Map<Long,UserArea> map = userAreaService.getMapUserAreaList(new UserArea());
    	List<UserAddress> userAddressList = userAddressDao.getUserAddressList(userAddress);
    	if(ObjectUtils.isNotNull(userAddressList)){
    		for(UserAddress address :userAddressList){
    			UserArea areaC = map.get(address.getCityId());
    			UserArea areaP = map.get(address.getProvinceId());
    			UserArea areaT = map.get(address.getTownId());
    			if(ObjectUtils.isNotNull(areaC)){
    				address.setCityStr(areaC.getAreaName());
    			}
    			if(ObjectUtils.isNotNull(areaP)){
    				address.setProvinceStr(areaP.getAreaName());
    			}
    			if(ObjectUtils.isNotNull(areaT)){
    				address.setTownStr(areaT.getAreaName());
    			}
    		}
    	}
    	return userAddressList;
    }

	@Override
	public void updateUserAddressisFirst(UserAddress userAddress) {
		// TODO Auto-generated method stub
		userAddressDao.updateUserAddressisFirst(userAddress);
	}
}