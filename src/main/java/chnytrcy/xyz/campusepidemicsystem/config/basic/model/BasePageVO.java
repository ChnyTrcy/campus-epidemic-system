package chnytrcy.xyz.campusepidemicsystem.config.basic.model;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.basic.model
 * @ClassName: BasePageVO
 * @Author: ChnyTrcy
 * @Description: 分页基本VO类
 * @Date: 2022/8/25 10:01 AM
 * @Version: 1.0
 */
@ApiModel("公共分页返回对象")
public class BasePageVO<T> implements Serializable {

  public static final Long defaultPageNum = 1L;
  public static final Long defaultPageSize = 10L;
  public static final List defaultEmptyList = new ArrayList();
  public static final Long defaultTotalNum = 0L;
  public static final Long defaultTotalPages = 0L;

  private static final long serialVersionUID = 7299837643024323482L;
  @ApiModelProperty(
      value = "当前页数",
      example = "1"
  )
  private Long curPage;
  @ApiModelProperty(
      value = "当前页面展示条数",
      example = "1"
  )
  private Long size;
  @ApiModelProperty(
      value = "总页数",
      example = "1"
  )
  private Long totalPage;
  @ApiModelProperty(
      value = "总记录数",
      example = "1"
  )
  private Long total;
  @ApiModelProperty("当前页列表")
  private List<T> data;

  public BasePageVO(PageInfo<T> page) {
    this.total = page.getTotal();
    this.totalPage = (long)page.getPages();
    this.data = page.getList();
    this.size = (long)page.getPageSize();
    this.curPage = (long)page.getPageNum();
  }

  public BasePageVO(PageInfo page, List<T> list) {
    this.total = page.getTotal();
    this.totalPage = (long)page.getPages();
    this.data = list;
    this.size = (long)page.getPageSize();
    this.curPage = (long)page.getPageNum();
  }

  public BasePageVO() {
    this.curPage = defaultPageNum;
    this.size = defaultPageSize;
    this.data = defaultEmptyList;
    this.totalPage = defaultTotalPages;
    this.total = defaultTotalNum;
  }

  public BasePageVO(PageInfo page, List<T> list, Long total, boolean calculatePage) {
    if (calculatePage) {
      this.total = total;
      this.totalPage = total % (long)page.getPageSize() == 0L ? total / (long)page.getPageSize() : total / (long)page.getPageSize() + 1L;
      this.data = list;
      this.size = (long)page.getPageSize();
      this.curPage = (long)page.getPageNum();
    } else {
      this.total = page.getTotal();
      this.totalPage = (long)page.getPages();
      this.data = list;
      this.size = (long)page.getPageSize();
      this.curPage = (long)page.getPageNum();
    }

  }

  public BasePageVO(PageInfo<T> page, Long total, boolean calculatePage) {
    if (calculatePage) {
      this.total = total;
      this.totalPage = total % (long)page.getPageSize() == 0L ? total / (long)page.getPageSize() : total / (long)page.getPageSize() + 1L;
      this.data = page.getList();
      this.size = (long)page.getPageSize();
      this.curPage = (long)page.getPageNum();
    } else {
      this.total = page.getTotal();
      this.totalPage = (long)page.getPages();
      this.data = page.getList();
      this.size = (long)page.getPageSize();
      this.curPage = (long)page.getPageNum();
    }

  }

  public static BasePageVO build(PageInfo<Object> page) {
    return new BasePageVO(page);
  }

  public static BasePageVO build(List<Object> list, PageInfo pageInfo) {
    return new BasePageVO(pageInfo, list);
  }

  public static BasePageVO buildEmpty() {
    return new BasePageVO();
  }

  public static  BasePageVO build(List<Object> list, PageInfo pageInfo, Long total, boolean calculatePage) {
    return new BasePageVO(pageInfo, list, total, calculatePage);
  }

  public static BasePageVO build(PageInfo<Object> page, Long total, boolean calculatePage) {
    return new BasePageVO(page, total, calculatePage);
  }

  public void setCurPage(Long curPage) {
    this.curPage = curPage;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public void setTotalPage(Long totalPage) {
    this.totalPage = totalPage;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public Long getCurPage() {
    return this.curPage;
  }

  public Long getSize() {
    return this.size;
  }

  public Long getTotalPage() {
    return this.totalPage;
  }

  public Long getTotal() {
    return this.total;
  }

  public List<T> getData() {
    return this.data;
  }
}
