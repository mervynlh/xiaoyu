

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yizhilu.os.edu.dao.order.TrxorderDao;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.service.coupon.CouponCodeService;
import com.yizhilu.os.edu.service.order.TrxorderService;

/**
 * 
 * @author huan.liu
 * @ClassName TestService
 * @package 
 * @description TODO
 * @Create Date: 2015-8-20 下午12:00:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
public class TestService {

    @Autowired
    private TrxorderDao trxorderDao;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private CouponCodeService couponCodeService;
    @Test
    public void test() {
        try {
        	/*Trxorder test1 = trxorderService.getTrxorderById(24L);
        	Trxorder test2 = trxorderService.getTrxorderById(24L);
        	test1.setTrxStatus("INIT");
        	trxorderDao.updateTrxorderStatusSuccess(test1);
        	test2.setTrxStatus("SUCCESS");
        	trxorderDao.updateTrxorderStatusSuccess(test2);*/
        	CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(210L);
        	System.out.println(couponCodeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
