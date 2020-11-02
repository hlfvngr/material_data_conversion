package com.hgits.hotc.entity.old;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "buildingmaterials")
@ApiModel(value="Buildingmaterials对象", description="")
public class OldBuildingmaterials implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer requisition;

    private String deliverynumber;

    private String deliveryname;

    private String deliveryadress;

    private String projecttime;

    private String constructionteam;

    private String major;

    private String deliverytype;

    private String demandclaimant;

    private String engineeringnumber;

    private String demandclaimantphone;

    private String singlepointname;

    private String construction;

    private String constructionunit;

    private Integer portnumber;

    private String town;

    private String acquisitiondate;

    private String area;

    private String tissue;

    private String batch;

    private String scene;

    private String cost;

    private String projectname;

    private String wesnumber;

    private String approver;

    private String opinion;

    private String role;

    private Integer state;

    private String memo;

    private String submitname;

    private String submitunit;

    private String submitmajor;

    private String upusername;

}
