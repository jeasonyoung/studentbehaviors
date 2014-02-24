package ipower.studentbehaviors.service.impl;

import java.util.List;

import ipower.studentbehaviors.dao.ISchoolDao;
import ipower.studentbehaviors.domain.School;
import ipower.studentbehaviors.service.ISchoolService;

/**
 * 学校服务实现。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public class SchoolServiceImpl implements ISchoolService {
	private ISchoolDao schoolDao;
	
	@Override
	public void setSchoolDao(ISchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	@Override
	public School load() {
		List<School> list = this.schoolDao.find("from School sch", null, null, null);
		if(list != null && list.size() > 0) return list.get(0);
		return null;
	}

	@Override
	public void update(School data) {
		if(data != null){
			School source = this.load();
			if(source == null){
				this.schoolDao.save(data);
			}else {
				source.setCode(data.getCode());
				source.setName(data.getName());
				source.setType(data.getType());
			}
		}
	}

}