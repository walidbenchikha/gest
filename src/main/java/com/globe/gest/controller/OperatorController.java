//package com.globe.gest.controller;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Locale;
//import java.util.Set;
//
//import javax.validation.Valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.globe.gest.exception.DuplicatePermissionException;
//import com.globe.gest.exception.PermissionNotFoundException;
//import com.globe.gest.exception.RoleNotFoundException;
//import com.globe.gest.model.Operator;
//import com.globe.gest.model.Role;
//import com.globe.gest.service.OperatorService;
//import com.globe.gest.service.PermissionService;
//import com.globe.gest.service.RoleService;
//
//@Controller
//@RequestMapping(value = "/operator")
//@PreAuthorize("denyAll")
//public class OperatorController {
//
//    static Logger logger = LoggerFactory.getLogger(OperatorController.class);
//    static String businessObject = "operator"; //used in RedirectAttributes messages 
//    
//
//    @Autowired
//    private OperatorService operatorService;
//
//    @Autowired
//    private MessageSource messageSource;
//
//
//    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
//    @PreAuthorize("hasRole('CTRL_PERM_LIST_GET')")
//    public String listOperators(Model model) {
//        logger.debug("IN: Operator/list-GET");
//
//        List<Operator> operators = operatorService.getOperators();
//        model.addAttribute("operators", operators);
//
//        // if there was an error in /add, we do not want to overwrite
//        // the existing user object containing the errors.
//        if (!model.containsAttribute("operatorDTO")) {
//            logger.debug("Adding OperatorDTO object to model");
//            OperatorDTO operatorDTO = new OperatorDTO();
//            model.addAttribute("operatorDTO", operatorDTO);
//        }
//        return "operator-list";
//    }
//    
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('CTRL_PERM_ADD_POST')")
//    public String addOperator(@Valid @ModelAttribute OperatorDTO operatorDTO,
//            BindingResult result, RedirectAttributes redirectAttrs) {
//        
//        logger.debug("IN: Operator/add-POST");
//        logger.debug("  DTO: " + operatorDTO.toString());
//
//        if (result.hasErrors()) {
//            logger.debug("OperatorDTO add error: " + result.toString());
//            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.operatorDTO", result);
//            redirectAttrs.addFlashAttribute("operatorDTO", operatorDTO);
//            return "redirect:/operator/list";
//        } else {
//            Operator perm = new Operator();
//
//            try {
//                perm = getOperator(operatorDTO);
//                operatorService.addOperator(perm);
//                String message = messageSource.getMessage("ctrl.message.success.add", 
//                        new Object[] {businessObject, perm.getOperatorname()}, Locale.US);
//                redirectAttrs.addFlashAttribute("message", message);
//                return "redirect:/operator/list";
//            } catch (DuplicateOperatorException e) {
//                String message = messageSource.getMessage("ctrl.message.error.duplicate", 
//                        new Object[] {businessObject, operatorDTO.getOperatorname()}, Locale.US);
//                redirectAttrs.addFlashAttribute("error", message);
//                return "redirect:/operator/list";
//           } catch (RoleNotFoundException e) {
//               String message = messageSource.getMessage("ctrl.message.error.notfound", 
//                       new Object[] {"role ids", operatorDTO.getPermRoles().toString()}, Locale.US);
//               redirectAttrs.addFlashAttribute("error", message);
//                return "redirect:/operator/list";
//            }
//        }
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('CTRL_PERM_EDIT_POST')")
//    public String editOperator(@Valid @ModelAttribute OperatorDTO operatorDTO,
//            BindingResult result, RedirectAttributes redirectAttrs,
//            @RequestParam(value = "action", required = true) String action) {
//
//        logger.debug("IN: Operator/edit-POST: " + action);
//
//        if (action.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
//            String message = messageSource.getMessage("ctrl.message.success.cancel", 
//                    new Object[] {"Edit", businessObject, operatorDTO.getOperatorname()}, Locale.US);
//            redirectAttrs.addFlashAttribute("message", message);
//        } else if (result.hasErrors()) {
//            logger.debug("Operator-edit error: " + result.toString());
//            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.operatorDTO", result);
//            redirectAttrs.addFlashAttribute("operatorDTO", operatorDTO);
//            return "redirect:/operator/edit?id=" + operatorDTO.getId();
//        } else if (action.equals(messageSource.getMessage("button.action.save",  null, Locale.US))) {
//            logger.debug("Operator/edit-POST:  " + operatorDTO.toString());
//            try {
//                Operator operator = getOperator(operatorDTO);
//                operatorService.updateOperator(operator);
//                String message = messageSource.getMessage("ctrl.message.success.update", 
//                        new Object[] {businessObject, operatorDTO.getOperatorname()}, Locale.US);
//                redirectAttrs.addFlashAttribute("message", message);
//            } catch (DuplicateOperatorException unf) {
//                String message = messageSource.getMessage("ctrl.message.error.duplicate", 
//                        new Object[] {businessObject, operatorDTO.getOperatorname()}, Locale.US);
//                redirectAttrs.addFlashAttribute("error", message);
//                return "redirect:/operator/list";
//            } catch (OperatorNotFoundException unf) {
//                String message = messageSource.getMessage("ctrl.message.error.notfound", 
//                        new Object[] {businessObject, operatorDTO.getOperatorname()}, Locale.US);
//                redirectAttrs.addFlashAttribute("error", message);
//                return "redirect:/operator/list";
//            } catch (RoleNotFoundException unf) {
//                String message = messageSource.getMessage("ctrl.message.error.notfound", 
//                        new Object[] {"role ids", operatorDTO.getPermRoles().toString()}, Locale.US);
//                redirectAttrs.addFlashAttribute("error", message);
//                return "redirect:/operator/list";
//            }
//        }
//        return "redirect:/operator/list";
//    }
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('CTRL_PERM_EDIT_GET')")
//    public String editOperatorPage(@RequestParam(value = "id", required = true)
//            Integer id, Model model, RedirectAttributes redirectAttrs) {
//
//        logger.debug("IN: Operator/edit-GET:  ID to query = " + id);
//
//        try {
//            if (!model.containsAttribute("operatorDTO")) {
//                logger.debug("Adding operatorDTO object to model");
//                Operator perm = operatorService.getOperator(id);
//                OperatorDTO operatorDTO = getOperatorDTO(perm);
//                logger.debug("Operator/edit-GET:  " + operatorDTO.toString());
//                model.addAttribute("operatorDTO", operatorDTO);
//            }
//            return "operator-edit";
//        } catch (OperatorNotFoundException e) {
//            String message = messageSource.getMessage("ctrl.message.error.notfound", 
//                    new Object[] {"user id", id}, Locale.US);
//            model.addAttribute("error", message);
//            return "redirect:/operator/list";
//        }
//    }
//
//    
//    
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('CTRL_PERM_DELETE_GET')")
//    public String deleteOperator(
//            @RequestParam(value = "id", required = true) Integer id,
//            @RequestParam(value = "phase", required = true) String phase,
//            Model model, RedirectAttributes redirectAttrs) {
//
//        Operator operator;
//        try {
//            operator = operatorService.getOperator(id);
//        } catch (OperatorNotFoundException e) {
//            String message = messageSource.getMessage("ctrl.message.error.notfound", 
//                    new Object[] {"operator id", id}, Locale.US);
//            redirectAttrs.addFlashAttribute("error", message);
//            return "redirect:/operator/list";
//        }
//
//        logger.debug("IN: Operator/delete-GET | id = " + id + " | phase = " + phase + " | " + operator.toString());
//
//        if (phase.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
//            String message = messageSource.getMessage("ctrl.message.success.cancel", 
//                    new Object[] {"Delete", businessObject, operator.getOperatorname()}, Locale.US);
//            redirectAttrs.addFlashAttribute("message", message);
//            return "redirect:/operator/list";
//        } else if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
//            logger.debug("     deleting operator : " + operator.toString());
//            model.addAttribute("operator", operator);
//            return "operator-delete";
//        } else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
//            try {
//                operatorService.deleteOperator(operator.getId());
//                String message = messageSource.getMessage("ctrl.message.success.delete", 
//                        new Object[] {businessObject, operator.getOperatorname()}, Locale.US);
//                redirectAttrs.addFlashAttribute("message", message);
//                return "redirect:/operator/list";
//            } catch (OperatorNotFoundException e) {
//                String message = messageSource.getMessage("ctrl.message.error.notfound", 
//                        new Object[] {"operator id", id}, Locale.US);
//               redirectAttrs.addFlashAttribute("error", message);
//                return "redirect:/operator/list";
//           }
//        }
//
//        return "redirect:/operator/list";
//    }
//
//    @PreAuthorize("hasAnyRole('CTRL_PERM_EDIT_GET','CTRL_PERM_DELETE_GET')")
//    public OperatorDTO getOperatorDTO(Operator perm) {
//        List<Integer> roleIdList = new ArrayList<Integer>();
//        OperatorDTO permDTO = new OperatorDTO();
//        permDTO.setId(perm.getId());
//        permDTO.setOperatorname(perm.getOperatorname());
//        for (Role role : perm.getPermRoles()) {
//            roleIdList.add(role.getId());
//        }
//        permDTO.setPermRoles(roleIdList);
//        return permDTO;
//    }
//
//    @PreAuthorize("hasAnyRole('CTRL_PERM_ADD_POST','CTRL_PERM_EDIT_POST')")
//    public Operator getOperator(OperatorDTO operatorDTO) throws RoleNotFoundException {
//        Set<Role> roleList = new HashSet<Role>();
//        Operator perm = new Operator();
//        Role role = new Role();
//        
//        perm.setId(operatorDTO.getId());
//        perm.setOperatorname(operatorDTO.getOperatorname());
//        if (operatorDTO.getPermRoles() != null) {
//            for (Integer roleId : operatorDTO.getPermRoles()) {
//                role = roleService.getRole(roleId);
//                logger.debug("  ROLE: " + role.toString());
//                roleList.add(role);
//            }
//            perm.setPermRoles(roleList);
//        }
//        logger.debug("  PERM: " + perm.toString());
//        return perm;
//    }
//    
//
//}
