//
//  MainCollectionViewCell.swift
//  Statistics
//
//  Created by Florin Ionita on 1/26/18.
//  Copyright © 2018 dp. All rights reserved.
//

import UIKit
import PNChart

let TopCollectionViewCellIdentifier = "TopCollectionViewCellIdentifier"

class MainCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var topCollectionView: UICollectionView!
    @IBOutlet weak var chartsHolderView: UIView!

    var items = 0
    var pieChart: PNPieChart!
    
    override func awakeFromNib() {
        self.layoutIfNeeded()
        
        self.topCollectionView.backgroundView = UIView.init(frame: .zero)
        self.topCollectionView.backgroundColor = .clear
        
        self.topCollectionView.dataSource = self
        self.topCollectionView.delegate = self
        
        RequestManager.sharedInstance().makeCoursesRequest { (success) in
            self.items = RequestManager.sharedInstance().courses.count + 1
            self.topCollectionView.reloadData()
        }
        
        RequestManager.sharedInstance().makeFeedbackStudentsRequest { (success) in
            
        }
        
        RequestManager.sharedInstance().makeFeedbackTeachersRequest { (success) in
            
        }
        
        RequestManager.sharedInstance().makeAllAssigmentsNumberRequest { (success) in
            
        }
        
        RequestManager.sharedInstance().makeMaxAttachementsNumberForFile { (success) in
            
        }
        
        RequestManager.sharedInstance().makeNrAssigmentsThatHaveFileRequest { (success) in
            
        }
    }

}

extension MainCollectionViewCell: UICollectionViewDataSource, UICollectionViewDelegate ,UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.items
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: TopCollectionViewCellIdentifier, for: indexPath)
        
        if let topCell = cell as? TopCollectionViewCell {
            if indexPath.item < self.items - 1 {
                let course = RequestManager.sharedInstance().courses[indexPath.row]
                topCell.titleLabel.text = course[Parser.name] as? String
                topCell.nameLabel.text = course[Parser.teacherName] as? String
                topCell.startsLabel.text = course[Parser.startDate] as? String
                topCell.endsLabel.text = course[Parser.endDate] as? String
                topCell.yearLabel.text = course[Parser.yearOfStudy] as? String
                
                self.generateChartForCourse(course)
            } else {
                topCell.titleLabel.text = "General"
                topCell.titleLabel.text = "Info"
                topCell.startsLabel.isHidden = true
                topCell.endsLabel.isHidden = true
                topCell.yearLabel.isHidden = true
                
                self.generateChartForGeneral()
            }
        }
        
        return cell
    }
    
    fileprivate func generateChartForCourse(_ course: [String: Any]) {
        
        if let chart = self.pieChart {
            chart.removeFromSuperview()
        }
        
        let items = [
            PNPieChartDataItem(value: CGFloat(course[Parser.completed] as! Int), color: .green, description: "Completed"),
            PNPieChartDataItem(value: CGFloat(course[Parser.notCompleted] as! Int), color: .red, description: "Not completed"),
            ]
        
        self.pieChart = PNPieChart.init(frame: self.chartsHolderView.bounds, items: items)!
        self.pieChart.descriptionTextColor = .black
        self.pieChart.descriptionTextFont = UIFont.init(name: "Avenir-Medium", size: 14)
        self.pieChart.stroke()
        
        
        self.chartsHolderView.addSubview(pieChart)
    }
    
    
    fileprivate func generateChartForGeneral() {
        
        if let chart = self.pieChart {
            chart.removeFromSuperview()
        }
        
        let items = [
            PNPieChartDataItem(value: CGFloat(RequestManager.sharedInstance().assigmentsThatHaveAttachements), color: .green, description: "Assigments with attachments."),
            PNPieChartDataItem(value: CGFloat(RequestManager.sharedInstance().feedbackStudents), color: .blue, description: "Feedback from Students"),
            PNPieChartDataItem(value: CGFloat(RequestManager.sharedInstance().feedbackTeachers), color: .yellow, description: "Feedback from Teachers"),
            PNPieChartDataItem(value: CGFloat(RequestManager.sharedInstance().totalAttachments), color: .lightGray, description: "Total attachments"),
            PNPieChartDataItem(value: CGFloat(RequestManager.sharedInstance().maxAttachments), color: .gray, description: "Max attachments"),
            ]
        
        self.pieChart = PNPieChart.init(frame: self.chartsHolderView.bounds, items: items)!
        self.pieChart.descriptionTextColor = .black
        self.pieChart.descriptionTextFont = UIFont.init(name: "Avenir-Medium", size: 14)
        self.pieChart.stroke()
        
        
        self.chartsHolderView.addSubview(pieChart)
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return collectionView.bounds.size
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return .zero
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 0
    }
    
}
